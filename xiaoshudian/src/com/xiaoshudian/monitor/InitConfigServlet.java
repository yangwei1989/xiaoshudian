package com.xiaoshudian.monitor;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.xiaoshudian.update.main.Main;
import com.xiaoshudian.util.Print;
/**
 * 用作定时任务，定时抓取数据
 */
public class InitConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final long TWO_HOUR = 2L * 60 * 60 * 1000;
    private Timer timer = null;
    private boolean started = false;
    
	@Override
	public void destroy() {
		super.destroy();
		timer.cancel();
		started = false;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		if( !started ){
			FetchTask task = new FetchTask();
			timer = new Timer("fetch-data-timer",false);
			timer.schedule(task, TWO_HOUR, TWO_HOUR);
			started = true;
		}
	}

	class FetchTask extends TimerTask{
		@Override
		public void run() {
			Print.print("begin fetch data...");
			Main.fetchSummary();
			Main.fetchArticle();
			Print.print("end fetch data...");
		}
	}
	
}
