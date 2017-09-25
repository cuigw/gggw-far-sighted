package com.gggw.cache.provider.redis;

import com.gggw.cache.core.Cache;
import com.gggw.cache.core.CacheException;

import java.util.List;

/**
 * 默认本地缓存实现
 *
 * @author cgw
 */
public class RedisCache implements Cache {

    @Override
    public Object get(Object key) throws CacheException {
        return null;
    }

    @Override
    public void put(Object key, Object value) throws CacheException {

    }

    @Override
    public void put(Object key, Object value, Integer expireInSec) throws CacheException {

    }

    @Override
    public void update(Object key, Object value) throws CacheException {

    }

    @Override
    public void update(Object key, Object value, Integer expireInSec) throws CacheException {

    }

    @Override
    public List keys() throws CacheException {
        return null;
    }

    @Override
    public void evict(Object key) throws CacheException {

    }

    @Override
    public void evict(List keys) throws CacheException {

    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public void destroy() throws CacheException {

    }
}
