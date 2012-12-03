package com.xiaoshudian.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.xiaoshudian.data.domain.ITArticle;
import com.xiaoshudian.data.provider.C3P0Utils;
import com.xiaoshudian.data.provider.DBUtil;

public class DBTimeFormatChange {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void changeTimeFormat() throws SQLException{
		
		Connection conn = C3P0Utils.getConnection();
		String sql = "select * from news_it where posttime not like '20%'";
		PreparedStatement pStatement = conn.prepareStatement(sql);
		ResultSet rSet = pStatement.executeQuery();
		List<ITArticle> articles = new ArrayList<ITArticle>();
		while( rSet.next() ){
			ITArticle a = new ITArticle();
			a.setId( rSet.getInt("id") );
			a.setTitle(rSet.getString("title"));
			a.setAuthor(rSet.getString("author"));
			a.setContent(rSet.getString("content"));
			a.setDetailurl(rSet.getString("detailurl"));
			a.setSitename(rSet.getString("sitename"));
			a.setPosttime(rSet.getString("posttime"));
			a.setFetchtime(rSet.getString("fetchtime"));
			a.setTag(rSet.getString("tag"));
			a.setFlag(rSet.getInt("flag"));
			
			articles.add(a);
		}
		
		pStatement.close();
		
		//update
		for( ITArticle a : articles ){
			
			String sql2 = "update news_it set posttime = ? where id = ?";
			pStatement = conn.prepareStatement(sql2);
			
			pStatement.setString(1, a.getFetchtime());
			pStatement.setLong(2,a.getId());
			
			pStatement.executeUpdate();
			pStatement.close();
			
		}
		
		conn.close();
	}
	
}
