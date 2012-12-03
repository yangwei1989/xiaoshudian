package com.xiaoshudian.data.cache;
/**
 * cache key 生成策略
 * 具体生成策略为： tableName_memcache_id
 * @author zhagya
 */
public class MemcacheKeyFactory implements CacheKeyFactory{

	@Override
	public String getKey(String table, long id) {
		return table + "_memcache_" + id ;
	}
	
}
