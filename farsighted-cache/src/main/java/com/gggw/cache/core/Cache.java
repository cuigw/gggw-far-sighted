package com.gggw.cache.core;

import java.util.List;

/**
 * 缓存接口
 *
 * @author cgw
 */
public interface Cache {

	/**
	 * 从cache中获取值
	 * @param key cache key
	 * @return the cached object or null
	 */
	public Object get(Object key) throws CacheException;
	
	/**
	 * 往cache中插入值
	 * failfast semantics
	 * @param key cache key
	 * @param value cache value
	 */
	public void put(Object key, Object value) throws CacheException;
	
	/**
	 * 往cache中插入值，且有过期时间
	 * failfast semantics
	 * @param key
	 * @param value
	 * @param expireInSec  expire time. (seconds)
	 * @throws CacheException
	 */
	public void put(Object key, Object value, Integer expireInSec) throws CacheException;
	
	/**
	 * 更新
	 * @param key cache key
	 * @param value cache value
	 */
	public void update(Object key, Object value) throws CacheException;
	
	/**
	 * 更新（过期时间）
	 * @param key
	 * @param value
	 * @param expireInSec  expire time. (seconds)
	 * @throws CacheException
	 */
	public void update(Object key, Object value, Integer expireInSec) throws CacheException;

	@SuppressWarnings("rawtypes")
	public List keys() throws CacheException ;
	
	/**
	 * 删除
	 * @param key Cache key
	 * Remove an item from the cache
	 */
	public void evict(Object key) throws CacheException;
	
	/**
	 * 批量删除
	 * @param keys the cache keys to be evicted
	 */
	@SuppressWarnings("rawtypes")
	public void evict(List keys) throws CacheException;
	
	/**
	 * 清空
	 */
	public void clear() throws CacheException;
	
	/**
	 * 清理
	 */
	public void destroy() throws CacheException;
	
}
