package com.xiaoshudian.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import com.sina.sae.kvdb.SaeKV;
import com.xiaoshudian.util.StringUtils;

public class Test {

	@org.junit.Test
	public void test() throws Exception {
		
		String d = new SimpleDateFormat("yyyy-M-d").format(new Date());
		System.out.println( d );
		Date dd = new SimpleDateFormat("yyyy-M-d").parse("2012-1-1");
		System.out.println( dd );
		
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\p{Space}\\d{2}:\\d{2}:\\d{2}");
		System.out.println(pattern.matcher("2012-01-01 23:23:01").matches());
	}

	@org.junit.Test
	public void testTimer(){
		
		
	}
	
	@org.junit.Test
	public void testKV(){
		
		SaeKV kv = new SaeKV();
		kv.init();
		
		kv.set("click_123", 1L);
		kv.set("click_112", 2L);
		
		long c1 = kv.get("click_123");
		c1 += 1;
		kv.set("click_123", c1);
		
		Object objj = kv.get("click_567");
		long c2 = 0;
		if( objj != null ){
			c2 = StringUtils.String2Long(String.valueOf(objj));
		}
		c2 += 1;
		kv.set("click_567", c2);
		
		Map<String,Object> obj = kv.pkrget("click", 100, true, "click_");
		System.out.println( obj );
		
	}
	
	public static void main(String[] args) {
		
		Timer timer = new Timer("fetch-data-timer",false);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println( "i am running" );
			}
		}, 1000,1000);
		
	}
	
}
