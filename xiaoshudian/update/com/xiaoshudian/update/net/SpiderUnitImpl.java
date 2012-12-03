package com.xiaoshudian.update.net;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sina.sae.fetchurl.SaeFetchurl;
import com.xiaoshudian.update.config.ConfigConstance;
import com.xiaoshudian.update.config.UpdateConfig;
import com.xiaoshudian.util.Print;
import com.xiaoshudian.util.StringUtils;

/**
 * spider抓取单元 
 * @author zhangya
 */
public class SpiderUnitImpl implements SpiderUnit<SArticle>{

	private URLFactory<SArticle> factory;
	private UpdateConfig config;
	private static final SimpleDateFormat SDF = new SimpleDateFormat();
	private Persistance<SArticle> persistance = new SummaryPersistance();
	
	public SpiderUnitImpl( URLFactory<SArticle> factory ,UpdateConfig config){
		this.factory = factory;
		this.config = config;
		persistance.init();
	}
	
	@Override
	public void run() {
		while(true){
			SArticle data = factory.url();
			if( data == null ){
				break;
			}
			Print.print(Thread.currentThread() + ":" + data.getSimple_url() );
			
			//解析html
			SaeFetchurl fetchurl = new SaeFetchurl();
			//fetchurl.setReadTimeout(4);
			//fetchurl.setSendTimeout(4);
			String html = fetchurl.fetch(data.getSimple_url());
			
			if( fetchurl.getErrno() != 0 ){
				continue;
			}
			
			Document doc = Jsoup.parse(html);
			Elements title_eles = doc.select( config.get(ConfigConstance.TITLE) );
			if( (title_eles == null) || (title_eles.size() == 0) ){
				Print.print("title_eles=0");
				continue;
			}
			
			Elements url_eles = doc.select( config.get( ConfigConstance.URL ) );
			if( (url_eles == null) || (url_eles.size() == 0) ){
				Print.print("urls_eles=0");
				continue;
			}
			
			boolean isSameSize = ( title_eles.size() == url_eles.size() );
			if( !isSameSize ){
				Print.print( "[url:" + data.getSimple_url() + ",title=" + title_eles.size() +",url=" + url_eles.size() +"]" );
			}
			
			int size = Math.min(url_eles.size(), title_eles.size());
			for( int i = 0 ; i < size ; i++ ){
				String title = "";
				String url = "";
				if( "text".equals(config.get( ConfigConstance.TITLE_ATTR )) ){
					title = title_eles.get(i).html();
				}else{
					title = title_eles.get(i).attr( config.get( ConfigConstance.TITLE_ATTR ) );
				}
				
				if( "text".equals( config.get( ConfigConstance.URL_ATTR ) )){
					url = url_eles.get(i).html();
				}else{
					url = url_eles.get(i).attr(config.get(ConfigConstance.URL_ATTR));
				}
				
				//相对地址
				if( !url.startsWith("http") ){
					String baseurl = doc.baseUri();
					if( StringUtils.isBlank(baseurl) ){
						if( url.startsWith("/") ){
							baseurl = config.get( ConfigConstance.SITE_URL );
						}else{
							baseurl = config.get( ConfigConstance.BASE_URL );
						}
					}
					if( url.startsWith("/") && baseurl.endsWith("/") ){
						url = baseurl + url.substring(1);
					}else if( !url.startsWith("/") && !baseurl.endsWith("/") ){
						url = baseurl + "/" + url;
					}else{
						url = baseurl + url;
					}
				}
				
				data.setDetail_url(url);
				data.setTitle(title);
				SDF.applyPattern("yyyy-MM-dd HH:mm:ss");
				data.setFetchTime( SDF.format(new Date()) );
				
				data.setSiteName( config.get( ConfigConstance.SITE_NAME ) );
				data.setSiteIndexUrl( config.get( ConfigConstance.SITE_URL ) );
				
				write(data);
			}
			
		}
		persistance.destory();
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void write(SArticle data) {
		Print.print( data );
		persistance.persistance(data);
	}
	
}
