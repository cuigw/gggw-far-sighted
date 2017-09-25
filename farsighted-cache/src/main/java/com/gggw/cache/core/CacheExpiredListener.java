package com.gggw.cache.core;

/**
 * 侦听缓存中的某个记录超时
 * @author cgw
 */
public interface CacheExpiredListener {

	/**
	 * 当缓存中的某个对象超时被清除的时候触发
	 * @param region: Cache region name
	 * @param key: cache key
	 */
	public void notifyElementExpired(String region, Object key) ;

}
