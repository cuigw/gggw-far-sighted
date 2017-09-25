package com.gggw.cache.provider.redis;

import com.caocao.cache.core.Cache;
import com.caocao.cache.core.CacheException;
import com.caocao.cache.core.CacheExpiredListener;
import com.caocao.cache.core.CacheProvider;

import java.util.Properties;

/**
 * 默认本地缓存提供者
 *
 * @author cgw
 */
public class RedisCacheProvider implements CacheProvider {

    private final static RedisCache redisCache = new RedisCache();

    @Override
    public String name() {
        return "redis";
    }

    @Override
    public Cache buildCache(String regionName, boolean autoCreate, CacheExpiredListener listener)
            throws CacheException {
        return redisCache;
    }

    @Override
    public void start(Properties props) throws CacheException {

    }

    @Override
    public void stop() {

    }
}
