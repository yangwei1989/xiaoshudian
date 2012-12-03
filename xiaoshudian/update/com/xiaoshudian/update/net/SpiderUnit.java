package com.xiaoshudian.update.net;

public interface SpiderUnit<T> extends Runnable{
	
	public void init();
	
	public void write(T t);
	
}
