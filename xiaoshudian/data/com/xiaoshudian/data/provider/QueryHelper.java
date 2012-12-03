package com.xiaoshudian.data.provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xiaoshudian.data.cache.Cache;
import com.xiaoshudian.data.cache.CacheProvider;
import com.xiaoshudian.util.Print;

/**
 * DBUtils和C3P0的工具类，主要针对查询做了一下处理，并在此基�?��提供了cache的支�? * @author zhagya 
 * 目前支持两种cache，一种是在memcache级别，针对比较实时的数据，改动可能�?较大的， 另一种是KV
 * DB，主要针对改动可能�?较小的缓�? */
public class QueryHelper {
	
	//private static final Cache CACHE_MEMCACHED = new CacheProvider();
	
	public <T> List<T> query(String sql, Object[] params, Class<T> type) {
		String key = sql2key(sql, params);
		Print.print(key);
		if( isInCache(key) ){
			// use json 
		}
		QueryRunner queryRunner = new QueryRunner( C3P0Utils.CPDS );
		try {
			return queryRunner.query(sql, new BeanListHandler<T>(type),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public <T> T get(String sql,Class<T> type){
		try{
			QueryRunner queryRunner = new QueryRunner( C3P0Utils.CPDS );
			return queryRunner.query(sql, new BeanHandler<T>(type));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected Object getFromCache(String key){
		//return CACHE_MEMCACHED.get(key);
		return null;
	}
	
	protected boolean isInCache(String key){
		return getFromCache(key) != null;
	}
	
	protected String sql2key(String sql,Object[] params){
		String key = sql;
		for( Object param : params ){
			if( param instanceof String ){
				key = key.replaceFirst("\\?", "'" + param + "'");
			}else{
				key = key.replaceFirst("\\?", String.valueOf(param));
			}
		}
		return key;
	}
	
	public Connection getConnection(){
		return C3P0Utils.getConnection();
	}
	
	public void close( Connection conn , PreparedStatement statement , ResultSet rSet ){
		DbUtils.closeQuietly(conn, statement, rSet);
	}
	
}
