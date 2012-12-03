package com.xiaoshudian.data.cache;
/**
 * kv db cache key 生成工厂
 * @author zhagya
 */
public class KVDBcacheKeyFactory implements CacheKeyFactory{

	@Override
	public String getKey(String table, long id) {
		return table + "_kvdb_" + id;
	}

}
