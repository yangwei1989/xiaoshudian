package com.xiaoshudian.update.main;

import java.util.List;

import com.xiaoshudian.update.config.UpdateConfig;
import com.xiaoshudian.update.net.ContentSpiderImpl;
import com.xiaoshudian.update.net.ContentURLFactory;
import com.xiaoshudian.update.net.SArticle;
import com.xiaoshudian.update.net.Spider;
import com.xiaoshudian.update.net.SpiderImpl;
import com.xiaoshudian.update.net.SummaryURLFactory;
import com.xiaoshudian.update.net.URLFactory;

/**
 * spider
 * @author zhangya
 */
public class Main {
	
	public static void  fetchSummary(){
		UpdateConfig config = new UpdateConfig("config.data");
		List<String> fileNames = config.getFiles();
		for( String filePath : fileNames ){
			config.next(filePath);
			URLFactory<SArticle> simpleURLFactory = new SummaryURLFactory(config);
			Spider spider = new SpiderImpl(config,simpleURLFactory);
			spider.start();
			spider.end();
		}
	}
	
	public static void fetchArticle(){
		UpdateConfig config = new UpdateConfig("config.data");
		List<String> fileNames = config.getFiles();
		for( String filePath : fileNames ){
			config.next(filePath);
			URLFactory<SArticle> simpleURLFactory = new ContentURLFactory(config);
			Spider spider = new ContentSpiderImpl(config,simpleURLFactory);
			spider.start();
			spider.end();
		}
	}
	
	public static void main(String[] args) {
		
		fetchSummary();
		fetchArticle();
		
	}
	
}
