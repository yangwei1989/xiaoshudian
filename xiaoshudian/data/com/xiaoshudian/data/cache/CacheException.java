package com.xiaoshudian.data.cache;

/**
 * 缓存异常
 * @author zhagya
 */
public class CacheException extends RuntimeException{

	private static final long serialVersionUID = -7913457795986583448L;

	public CacheException() {
		super();
	}

	public CacheException(String message, Throwable cause) {
		super(message, cause);
	}

	public CacheException(String message) {
		super(message);
	}

	public CacheException(Throwable cause) {
		super(cause);
	}
	
}
