package com.xiaoshudian.data.cache;

import java.util.List;

/**
 * cache接口
 * @author zhagya
 */
public interface Cache {
	
	public Object get(Object key) throws CacheException;
	
	public void set(Object key,Object val) throws CacheException;
	
	public void update(Object key,Object val) throws CacheException;
	
	public List<Object> keys() throws CacheException;
	
	public void remove(Object key) throws CacheException;
	
	public void clear() throws CacheException;
	
	public void destory() throws CacheException;
	
}
