package com.xiaoshudian.update.net;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.xiaoshudian.update.config.ConfigConstance;
import com.xiaoshudian.update.config.UpdateConfig;
import com.xiaoshudian.util.Print;
import com.xiaoshudian.util.StringUtils;

public class ContentSpiderImpl implements Spider{

	private UpdateConfig updateConfig;
	private ExecutorService service;
	private URLFactory<SArticle> urlFactory;
	private int threadCount = 0;
	
	public ContentSpiderImpl(UpdateConfig config,URLFactory<SArticle> factory){
		this.updateConfig = config;
		this.urlFactory = factory;
		this.threadCount = StringUtils.String2Int(updateConfig.get(ConfigConstance.THREAD_COUNT));
		service = Executors.newFixedThreadPool( threadCount );
	}
	
	@Override
	public void start() {
		for( int i = 1 ; i <= threadCount ; i++ ){
			service.execute( new ContentSpiderUnitImpl(urlFactory,updateConfig) );
		}
	}
	
	@Override
	public void end() {
		try {
			service.shutdown();
			service.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Print.print( "shut down ..." );
	}

}
