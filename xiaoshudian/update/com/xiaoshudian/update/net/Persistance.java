package com.xiaoshudian.update.net;
/**
 * 数据持久化接口
 * @author zhangya
 */
public interface Persistance<T> {
	
	public void init();
	
	public void persistance(T data);
	
	public void destory();
	
}
