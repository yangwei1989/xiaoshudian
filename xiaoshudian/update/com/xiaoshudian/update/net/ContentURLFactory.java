package com.xiaoshudian.update.net;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.xiaoshudian.data.provider.DBUtil;
import com.xiaoshudian.update.config.ConfigConstance;
import com.xiaoshudian.update.config.UpdateConfig;

/**
 * 抓取内容 URL 生成器
 * @author zhangya
 */
public class ContentURLFactory implements URLFactory<SArticle>{
	
	private List<SArticle> urls = new CopyOnWriteArrayList<SArticle>();
	private UpdateConfig config;
	private DBUtil dbHelper = new DBUtil();
	
	public ContentURLFactory(UpdateConfig config){
		this.config = config;
		String table = config.get( ConfigConstance.TABLE );
		String sitename = config.get( ConfigConstance.SITE_NAME );
		String sql = "select id,title,url,detailurl,sitename,siteurl,fetchtime,flag from " + table + " where sitename = ? and flag = 0 ";
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		try{
			conn = dbHelper.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, sitename);
			rSet = statement.executeQuery();
			while( rSet.next() ){
				int id = rSet.getInt("id");
				String title = rSet.getString("title");
				String url = rSet.getString("url");
				String detailurl = rSet.getString("detailurl");
				String site_name = rSet.getString("sitename");
				String siteurl = rSet.getString("siteurl");
				String fetchtime = rSet.getString("fetchtime");
				int flag = rSet.getInt("flag");
				
				SArticle data = new SArticle();
				data.setId(id);
				data.setTitle(title);
				data.setSimple_url(url);
				data.setDetail_url(detailurl);
				data.setSiteName(site_name);
				data.setSiteIndexUrl(siteurl);
				data.setFetchTime(fetchtime);
				data.setFlag(flag);
				
				urls.add(data);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbHelper.close(conn, statement, rSet);
		}
		
	}
	
	
	@Override
	public synchronized SArticle url() {
		if( urls.isEmpty() ){
			return null;
		}
		return urls.remove(0);
	}

}
