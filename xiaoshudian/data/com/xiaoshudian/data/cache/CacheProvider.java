package com.xiaoshudian.data.cache;

import java.util.Collections;
import java.util.List;

import com.sina.sae.memcached.SaeMemcache;

/**
 * cache provider
 * @author zhagya
 */
public class CacheProvider implements Cache{

	private static final SaeMemcache MEMCACHE = new SaeMemcache();
	
	private static final long EXPIRY = 1000L * 60 *30;  //30分钟
	
	static{
		MEMCACHE.init();
	}
	
	@Override
	public Object get(Object key) throws CacheException {
		try{
			return MEMCACHE.get(String.valueOf(key));
		}catch (Exception e) {
			throw new CacheException("[memcache][get][error]key:" + key , e);
		}
	}
	
	@Override
	public void set(Object key, Object val) throws CacheException {
		try{
			MEMCACHE.add((String)key, val, EXPIRY);
		}catch (Exception e) {
			throw new CacheException("[memcache][set][error]key:" + key + ",value=" + val,e);
		}
	}

	@Override
	public void update(Object key, Object val) throws CacheException {
		try{
			MEMCACHE.replace(String.valueOf(key), val,EXPIRY);
		}catch (Exception e) {
			throw new CacheException("[memcache][update][error]key:" + key + ",value=" + val,e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> keys() throws CacheException {
		try{
			return Collections.EMPTY_LIST;
		}catch (Exception e) {
			throw new CacheException("[memcache][keys][error]list all keys",e);
		}
	}

	@Override
	public void remove(Object key) throws CacheException {
		try{
			MEMCACHE.delete(String.valueOf(key));
		}catch (Exception e) {
			throw new CacheException("[memcache][remove][error]key:" + key,e);
		}
	}

	@Override
	public void clear() throws CacheException {
	}

	@Override
	public void destory() throws CacheException {
	}
	
}
