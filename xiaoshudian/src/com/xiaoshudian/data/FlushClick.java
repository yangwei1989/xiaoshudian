package com.xiaoshudian.data;

import java.util.Map;

import com.xiaoshudian.data.cache.KVCacheProvider;

public class FlushClick {

	public static void flush(){
		
		KVCacheProvider cache = KVCacheProvider.getInstance();
		Map<String,Object> map = cache.getByPrefix("click", 100,false, "click_");
		for( String key : map.keySet() ){
			System.out.println("key=" + key +  ",ser=" + (cache.get(key)));
		}
		
	}
	
	public static void main(String[] args) {
		
		flush();
		
	}
	
}
