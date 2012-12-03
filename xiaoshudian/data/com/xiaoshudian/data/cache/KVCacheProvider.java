package com.xiaoshudian.data.cache;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.sina.sae.kvdb.SaeKV;
import com.sina.sae.kvdb.SaeKVUtil;
/**
 * KV DB缓存提供类 * @author zhagya
 */
public class KVCacheProvider implements Cache{

	private static final SaeKV SAE_KV = new SaeKV();
	private static final long EXPIRY = 1000L * 60 * 60 * 5;	//5 hour
	private static final KVCacheProvider CACHE = new KVCacheProvider();
	
	private KVCacheProvider(){}
	
	static{
		SAE_KV.init();
	}

	public static KVCacheProvider getInstance(){
		return CACHE;
	}
	
	@Override
	public Object get(Object key) throws CacheException {
		try{
			Object obj = SAE_KV.get(String.valueOf(key));
			if( obj == null ){
				return null;
			}
			//serialize to string
			//return (String)SaeKVUtil.deserializable(obj);
			return SaeKVUtil.objToString(obj);
		}catch (Exception e) {
			e.printStackTrace();
			throw new CacheException("[KVDB get] key:" + key , e.getCause());
		}
	}
	
	@Override
	public void set(Object key, Object val) throws CacheException {
		try{
			byte[] bs = SaeKVUtil.StringToByte(String.valueOf(val));
			SAE_KV.set(String.valueOf(key), bs,EXPIRY);
		}catch (Exception e) {
			e.printStackTrace();
			throw new CacheException("[KVDB set] key:" + key + ",val=" + val,e.getCause());
		}
	}

	@Override
	public void update(Object key, Object val) throws CacheException {
		try{
			remove(key);
			set(key, val);
		}catch (Exception e) {
			e.printStackTrace();
			throw new CacheException("[KVDB update]key:" + key + ",val=" + val,e.getCause());
		}
	}
	
	@Override
	public List<Object> keys() throws CacheException {
		return Collections.emptyList();
	}
	
	@Override
	public void remove(Object key) throws CacheException {
		try{
			SAE_KV.delete(String.valueOf(key));
		}catch (Exception e) {
			e.printStackTrace();
			throw new CacheException("[KVDB remove]key:" + key,e.getCause());
		}
	}
	
	public Map<String,Object> getByPrefix(String prefix,int count,boolean flag,String prefix_){
		return SAE_KV.pkrget(prefix,count,flag,prefix_);
	}
	
	@Override
	public void clear() throws CacheException {
	}
	
	@Override
	public void destory() throws CacheException {
	}
	
}
