package com.xiaoshudian.update.net;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sina.sae.fetchurl.SaeFetchurl;
import com.xiaoshudian.update.config.ConfigConstance;
import com.xiaoshudian.update.config.UpdateConfig;
import com.xiaoshudian.util.Print;
import com.xiaoshudian.util.StringUtils;

public class ContentSpiderUnitImpl implements SpiderUnit<SArticle>{
	
	private URLFactory<SArticle> factory;
	private UpdateConfig config;
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Persistance<SArticle> persistance = new ContentPersistance();
	private Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\p{Space}\\d{2}:\\d{2}:\\d{2}");
	
	public ContentSpiderUnitImpl( URLFactory<SArticle> factory ,UpdateConfig config){
		this.factory = factory;
		this.config = config;
		persistance.init();
	}
	
	@Override
	public void run() {
		while( true ){
			SArticle data = factory.url();
			if( data == null || data.getDetail_url() == null){
				break ;
			}
			
			//fetch html
			SaeFetchurl fetchurl = new SaeFetchurl();
			String html = fetchurl.fetch( data.getDetail_url() );
			
			if( fetchurl.getErrno() != 0 ){
				Print.print( fetchurl.getErrmsg() );
				continue ;
			}
			
			Document doc = Jsoup.parse(html);
			//content
			Elements contents = doc.select(config.get(ConfigConstance.CONTENT));
			String content = "";
			int content_pos = StringUtils.String2Int( config.get( ConfigConstance.CONTENT_POS ) );
			if( contents.size() != 0 && contents.size() > content_pos ){
				content = contents.get(content_pos).html();
				data.setContent(content);
			}else{
				Print.print( "[Warn] detailurl: " + data.getDetail_url() + ",content:" + config.get(ConfigConstance.CONTENT) );
				// if no content , pass
				continue;
			}
			
			//author
			Elements authors = doc.select( config.get( ConfigConstance.AUTHOR ) );
			String author = "";
			int author_pos = StringUtils.String2Int( config.get( ConfigConstance.AUTHOR_POS ) );
			if( authors.size() != 0 && authors.size() > author_pos ){
				author = authors.get(author_pos).text();
				String author_prefix = config.get( ConfigConstance.AUTHOR_PREFIX );
				if( author_prefix != null && author.startsWith(author_prefix) ){
					author = author.substring(author_prefix.length());
				}
				data.setAuthor(author);
			}else{
				Print.print( "[Warn] detailurl: " + data.getDetail_url() + ",author:" + config.get(ConfigConstance.AUTHOR) );
				data.setAuthor(data.getSiteName());
			}
			
			//time
			Elements times = doc.select( config.get( ConfigConstance.TIME ) );
			String time = "";
			int time_pos = StringUtils.String2Int( config.get( ConfigConstance.TIME_POS ) );
			if( times.size() != 0 && times.size() > time_pos ){
				time = times.get(time_pos).text();
				String time_prefix = config.get( ConfigConstance.TIME_PREFIX );
				if( time_prefix != null && time.startsWith(time_prefix) ){
					time = time.substring(time_prefix.length());
				}
				String time_format = config.get( ConfigConstance.TIME_FORMAT );
				if( time_format != null ){
					try{
						SimpleDateFormat ss = new SimpleDateFormat(time_format);
						Date temp = ss.parse(time);
						time = SDF.format(temp);
					}catch (Exception e) {
						//pass
					}
				}
				if( !pattern.matcher(time).matches() ){
					time = SDF.format(new Date());
				}
				data.setTime(time);
				data.setPostTime(time);
			}else{
				Print.print( "[Warn] detailurl: " + data.getDetail_url() + ",time:" + config.get(ConfigConstance.TIME) );
				data.setTime(SDF.format(new Date()));
				data.setPostTime(SDF.format(new Date()));
			}
			
			//tag
			Elements tags = doc.select( config.get( ConfigConstance.TAG ) );
			int tag_pos = StringUtils.String2Int(config.get( ConfigConstance.TAG_POS ));
			String tag = "";
			if( tags.size() != 0 && tags.size() > tag_pos ){
				tag = tags.get(tag_pos).text();
				String tag_prefix = config.get( ConfigConstance.TAG_PREFIX );
				if( tag_prefix != null && tag.startsWith(tag_prefix) ){
					tag = tag.substring(tag_prefix.length());
				}
				data.setTag(tag);
			}else{
				Print.print( "[Warn] detailurl: " + data.getDetail_url() + ",tag:" + config.get(ConfigConstance.TAG) );
				data.setTag("");
			}
			
			//fetchtime
			data.setFetchTime( SDF.format(new Date()) );
			write(data);
		}
		persistance.destory();
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void write(SArticle t) {
		Print.print(t);
		persistance.persistance(t);
	}
	
}
