package com.xiaoshudian.data.provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * sql辅助�? * @author zhagya
 */

public class DBUtil {

//	public static final String DB_URL = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_xiaoshudian?useUnicode=true&characterEncoding=utf-8";
//	public static final String DB_USER = SaeUserInfo.getAccessKey();
//	public static final String DB_PASSWD = SaeUserInfo.getSecretKey();
//	public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/xiaoshudian?useUnicode=true&characterEncoding=utf-8";
//	public static final String DB_USER = "root";
//	public static final String DB_PASSWD = "root";
	
	public static final String DB_URL = "jdbc:mysql://xiaoshudian.mysql.rds.aliyuncs.com:3306/xiaoshudian?useUnicode=true&characterEncoding=utf-8";
	public static final String DB_USER = "xiaoshudian";
	public static final String DB_PASSWD = "wywmzyq";
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public Connection before(){
		return getConnection();
	}
	
	public void after(Connection conn){
		close(conn);
	}
	
	public void executeSql(String sql){
		
		Connection conn = null;
		PreparedStatement pStatement = null;
		try{
			conn = getConnection();
			pStatement = conn.prepareStatement(sql);
			pStatement.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(conn,pStatement);
		}
	}
	
	public ResultSet executeQuery(String sql){
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		try{
			conn = getConnection();
			pStatement = conn.prepareStatement(sql);
			rSet = pStatement.executeQuery();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(conn,pStatement);
		}
		return rSet;
	}
	
	public Connection getConnection(){
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void close(Connection conn){
		try{
			if( conn != null ){
				conn.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close( PreparedStatement ps ){
		try{
			if( ps != null ){
				ps.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close(ResultSet rset){
		try{
			if( rset != null ){
				rset.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close( Connection conn , PreparedStatement ps ){
		close(ps);
		close(conn);
	}
	
	public void close( Connection conn , PreparedStatement ps , ResultSet rSet ){
		close(ps);
		close(rSet);
		close(conn);
	}
	
}
