package com.gggw.cache.core;

/**
 * 自定义的缓存异常
 * @author cgw
 */
public class CacheException extends RuntimeException {

	private static final long serialVersionUID = -5112528854998647834L;

	public CacheException(String s) {
		super(s);
	}

	public CacheException(String s, Throwable e) {
		super(s, e);
	}

	public CacheException(Throwable e) {
		super(e);
	}
	
}
