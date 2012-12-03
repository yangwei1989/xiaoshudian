package com.xiaoshudian.data.cache;

/**
 * cache key 生成工厂
 * 单个对象的cache key生成方法，如�?��存数据库结果，请使用处理过的sql语句作为key
 * @author zhagya
 */
public interface CacheKeyFactory {

	public String getKey(String table , long id);
	
}
