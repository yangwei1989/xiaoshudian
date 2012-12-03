package com.xiaoshudian.update.net;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.xiaoshudian.data.provider.DBUtil;

public class SummaryPersistance implements Persistance<SArticle>{

	private Connection conn;
	private DBUtil dbHelper;
	
	@Override
	public void persistance(SArticle data) {
		if( exists(data) ){
			return ;
		}
		try{
			String sql = "insert into spider_article(title,url,detailurl,sitename,siteurl,fetchtime) values(?,?,?,?,?,?)";
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, data.getTitle());
			pStatement.setString(2, data.getSimple_url());
			pStatement.setString(3, data.getDetail_url());
			pStatement.setString(4, data.getSiteName());
			pStatement.setString(5, data.getSiteIndexUrl());
			pStatement.setString(6, data.getFetchTime());
			pStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean exists(SArticle data){
		try{
			String sql = "select count(*) from spider_article where title=? and sitename = ?";
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, data.getTitle());
			pStatement.setString(2, data.getSiteName());
			ResultSet rset = pStatement.executeQuery();
			int cnt = 0;
			if( rset.next() ){
				cnt = rset.getInt(1);
			}
			return cnt > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void init() {
		dbHelper = new DBUtil();
		conn = dbHelper.getConnection();
	}
	
	@Override
	public void destory() {
		dbHelper.close(conn);
	}
	
}
