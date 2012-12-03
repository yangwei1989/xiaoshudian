package com.xiaoshudian.update.net;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.xiaoshudian.data.provider.DBUtil;

public class ContentPersistance implements Persistance<SArticle>{

	private Connection conn;
	private DBUtil dbHelper;
	
	@Override
	public void init() {
		dbHelper = new DBUtil();
		conn = dbHelper.getConnection();
	}

	@Override
	public void persistance(SArticle data) {
		String sql = "";
		try{
			sql = "update `spider_article` set flag = 1 where id = ?";
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, data.getId());
			pStatement.executeUpdate();
			pStatement.close();
			
			sql = "insert into `news_it`(title,author,content,detailurl,sitename,posttime,fetchtime,tag,flag) values(?,?,?,?,?,?,?,?,?)";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, data.getTitle());
			pStatement.setString(2, data.getAuthor());
			pStatement.setString(3, data.getContent());
			pStatement.setString(4, data.getDetail_url());
			pStatement.setString(5, data.getSiteName());
			pStatement.setString(6, data.getPostTime());
			pStatement.setString(7, data.getFetchTime());
			pStatement.setString(8, data.getTag());
			pStatement.setInt(9, 0);
			pStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void destory() {
		dbHelper.close(conn);
	}
	
}
