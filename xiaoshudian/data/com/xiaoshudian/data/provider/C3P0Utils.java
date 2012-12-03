package com.xiaoshudian.data.provider;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sina.sae.util.SaeUserInfo;

/**
 * C3P0工具�? * @author zhagya
 */
public class C3P0Utils {

	//public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/xiaoshudian?useUnicode=true&characterEncoding=utf-8";
	//public static final String DB_USER = SaeUserInfo.getAccessKey();
	//public static final String DB_PASSWD = SaeUserInfo.getSecretKey();
	
	//public static final String DB_USER = "root";
	//public static final String DB_PASSWD = "root";
	
	public static final String DB_URL = "jdbc:mysql://xiaoshudian.mysql.rds.aliyuncs.com:3306/xiaoshudian?useUnicode=true&characterEncoding=utf-8";
	public static final String DB_USER = "xiaoshudian";
	public static final String DB_PASSWD = "wywmzyq";
	
	public static ComboPooledDataSource CPDS = new ComboPooledDataSource();

	static {
		try {
			CPDS.setDriverClass("com.mysql.jdbc.Driver");
			CPDS.setJdbcUrl(DB_URL);
			CPDS.setUser(DB_USER);
			CPDS.setPassword(DB_PASSWD);
			CPDS.setMaxStatements(500);
			CPDS.setMaxPoolSize(200);
			CPDS.setAcquireIncrement(5);
			CPDS.setCheckoutTimeout( 25 );
			CPDS.setNumHelperThreads( 5 );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = CPDS.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn){
		try{
			if( conn != null ){
				conn.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
