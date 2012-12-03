package com.xiaoshudian.data.domain;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.xiaoshudian.data.cache.Cache;
import com.xiaoshudian.data.cache.CacheKeyFactory;
import com.xiaoshudian.data.cache.KVCacheProvider;
import com.xiaoshudian.data.cache.KVDBcacheKeyFactory;
import com.xiaoshudian.data.provider.QueryHelper;
import com.xiaoshudian.util.Print;

/**
 * 数据库对象基�? * @author zhagya
 */
public class POJO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final QueryHelper QUERY_HELPER = new QueryHelper();
	private static final CacheKeyFactory KEY_FACTORY = new KVDBcacheKeyFactory();
	private static final Cache CACHE = KVCacheProvider.getInstance();
	private long id;
	
	protected String getTableName() {
		return null;
	}
	
	public void removeThisFromCache() {
		CACHE.remove(getKey(this.id));
	}
	
	public void removeFromCacheById(long id) {
		CACHE.remove(getKey(id));
	}
	
	protected String getKey(long tid) {
		return KEY_FACTORY.getKey(getTableName(), tid);
	}
	
	public String fromCache() {
		return String.valueOf(CACHE.get(getKey(this.id)));
	}
	
	/**
	 * Notice : page �?�?��
	 */
	public List<? extends POJO> listByPage(int page, int size) {
		return filterByPage(null, page, size);
	}
	
	public List<? extends POJO> specifyByPage(String column,String wherecase,int page,int size){
		String sql = "";
		if( column == null || column.isEmpty() ){
			column = "*";
		}
		if( wherecase == null || wherecase.trim().isEmpty() ){
			sql = "select " + column + " from " + getTableName() + " limit ?,?";
		}else{
			sql = "select " + column + " from " + getTableName() + wherecase + " limit ?,?";
		}
		Object[] params = { getLimit(page, size), size };
		return QUERY_HELPER.query(sql, params, this.getClass());
	}
	
	public List<? extends POJO> filterByPage(String wherecase, int page, int size) {
		String sql = "";
		if (wherecase == null || wherecase.trim().isEmpty()) {
			sql = "select * from " + getTableName() + " limit ?,?";
		} else {
			sql = "select * from " + getTableName() + wherecase + " limit ?,?";
		}
		Object[] params = { getLimit(page, size), size };
		return QUERY_HELPER.query(sql, params, this.getClass());
	}

	public int getLimit(int page, int size) {
		if (page <= 0) {
			throw new IllegalArgumentException("page must begin from 1");
		}
		return (page - 1) * size;
	}
	
	public long totalCount() {
		String sql = "select count(*) from " + getTableName();
		Connection conn = QUERY_HELPER.getConnection();
		long count = 0L;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		try {
			pStatement = conn.prepareStatement(sql);
			rSet = pStatement.executeQuery();
			if( rSet.next() ){
				count = rSet.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			QUERY_HELPER.close(conn, pStatement, rSet);
		}
		return count;
	}

	/**
	 * 是否被缓�?	 */
	public boolean cached() {
		return (CACHE.get(getKey(this.id)) != null);
	}
	
	/**
	 * 是否�?��缓存
	 */
	public boolean needCached() {
		return true;
	}
	
	/**
	 * 从数据库中删除此记录
	 */
	public void delete() {
		String sql = "delete from " + getTableName() + " where id=" + this.id;
		Connection conn = QUERY_HELPER.getConnection();
		PreparedStatement pStatement = null;
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			QUERY_HELPER.close(conn, pStatement, null);
		}
	}
	
	public <T extends POJO> T get() {
		return getById(this.id);
	}

	@SuppressWarnings("unchecked")
	public <T extends POJO> T getById(long id) {
		String sql = "select * from " + getTableName() + " where id=" + id;
		return (T) QUERY_HELPER.get(sql, this.getClass());
	}

	@SuppressWarnings("unchecked")
	public <T extends POJO> List<T> getByIds(List<Long> ids) {
		String sql = "select * from " + getTableName() + " where id in (";
		for( long id : ids ){
			sql += " " + id + " ,";
		}
		sql.substring(0, sql.length() - 1);
		sql += ")";
		return (List<T>) QUERY_HELPER.query(sql, null, this.getClass());
	}

	public long insert() {
		Map<String,Object> field = ListFields();
		String sql = "insert into " + getTableName() + "(";
		for( String key : field.keySet() ){
			sql += ("`" + key + "`" + ",");
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += ") values(";
		for( String key : field.keySet() ){
			sql += "'" + String.valueOf(field.get(key)) + "',";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += ")";
		Print.print(sql);
		Connection conn = QUERY_HELPER.getConnection();
		PreparedStatement pStatement = null;
		try {
			pStatement = conn.prepareStatement(sql);
			return pStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			QUERY_HELPER.close(conn, pStatement, null);
		}
		return 0L;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> ListFields() {
		try {
			Map<String, Object> properties;
			properties = BeanUtils.describe(this);
			if (this.getId() <= 0) {
				properties.remove("id");
			}
			properties.remove("class");
			return properties;
		} catch (Exception e) {
			throw new RuntimeException("[Error in extra field] detail :" + e.getMessage() + ",this=" + this);
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		long otherId = ((POJO) obj).getId();
		return (otherId == this.getId());
	}

}
