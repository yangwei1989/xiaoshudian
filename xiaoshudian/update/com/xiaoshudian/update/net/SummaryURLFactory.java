package com.xiaoshudian.update.net;

import com.xiaoshudian.update.config.ConfigConstance;
import com.xiaoshudian.update.config.UpdateConfig;
import com.xiaoshudian.util.StringUtils;

/**
 * 生成抓取数据库的数据
 * @author zhangya
 */
public class SummaryURLFactory implements URLFactory<SArticle>{

	private UpdateConfig config;
	private volatile int currentPage;
	private volatile int minPage ;
	private volatile int maxPage ;
	
	public SummaryURLFactory(UpdateConfig config){
		this.config = config;
		minPage = StringUtils.String2Int(config.get( ConfigConstance.MIN_PAGE ));
		maxPage = StringUtils.String2Int(config.get( ConfigConstance.MAX_PAGE ));
		currentPage = minPage;
	}
	
	@Override
	public synchronized SArticle url() {
		if( currentPage > maxPage || currentPage < minPage ){
			return null;
		}else{
			SArticle data = new SArticle();
			String url = config.get(ConfigConstance.BASE_URL) + config.get(ConfigConstance.PAGE_SUFFIX) + currentPage;
			data.setSimple_url(url);
			currentPage += 1;
			return data;
		}
	}

}
