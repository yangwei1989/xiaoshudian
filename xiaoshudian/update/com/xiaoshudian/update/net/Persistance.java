package com.xiaoshudian.update.net;
/**
 * ���ݳ־û��ӿ�
 * @author zhangya
 */
public interface Persistance<T> {
	
	public void init();
	
	public void persistance(T data);
	
	public void destory();
	
}
