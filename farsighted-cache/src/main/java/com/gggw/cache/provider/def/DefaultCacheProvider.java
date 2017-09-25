package com.gggw.cache.provider.def;

import com.gggw.cache.core.Cache;
import com.gggw.cache.core.CacheException;
import com.gggw.cache.core.CacheExpiredListener;
import com.gggw.cache.core.CacheProvider;

import java.util.Properties;

/**
 * 默认本地缓存提供者
 *
 * @author cgw
 */
public class DefaultCacheProvider implements CacheProvider {

    private final static DefaultCache defaultCache = new DefaultCache();

    @Override
    public String name() {
        return "default";
    }

    @Override
    public Cache buildCache(String regionName, boolean autoCreate, CacheExpiredListener listener)
            throws CacheException {
        return defaultCache;
    }

    @Override
    public void start(Properties props) throws CacheException {

    }

    @Override
    public void stop() {

    }
}
