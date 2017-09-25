package com.gggw.cache.core;

import com.caocao.cache.CcCache;
import com.caocao.cache.provider.def.DefaultCacheProvider;
import com.caocao.cache.provider.redis.RedisCacheProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * 缓存管理器
 *
 * @author cgw
 */
public class CacheManager {

    private final static Logger log = LoggerFactory.getLogger(CacheManager.class);

    //一级缓存
    private static CacheProvider l1_provider;
    //二级缓存
    private static CacheProvider l2_provider;

    //过期监听器
    private static CacheExpiredListener listener;

    //序列化方式
    private static String serializer;

    /**
     * 初始化缓存提供者
     *
     * @param listener
     */
    private static void initCacheProvider(CacheExpiredListener listener) {

        CacheManager.listener = listener;

        try {

            Properties props = CcCache.getConfig();

            CacheManager.l1_provider = getProviderInstance(props.getProperty("cache.L1.provider_class"));
            CacheManager.l1_provider.start(getProviderProperties(props, CacheManager.l1_provider));
            log.info("Using L1 CacheProvider : " + l1_provider.getClass().getName());

            CacheManager.l2_provider = getProviderInstance(props.getProperty("cache.L2.provider_class"));
            CacheManager.l2_provider.start(getProviderProperties(props, CacheManager.l2_provider));
            log.info("Using L2 CacheProvider : " + l2_provider.getClass().getName());

            CacheManager.serializer = props.getProperty("cache.serialization");

        } catch (Exception e) {
            throw new CacheException("Unabled to initialize cache providers", e);
        }

    }

    public final static String getSerializer() {
        return serializer;
    }

    /**
     * 获取缓存实现方式
     *
     * @param value
     * @return
     * @throws Exception
     */
    private final static CacheProvider getProviderInstance(String value) throws Exception {

        if ("redis".equalsIgnoreCase(value)) {
            return new RedisCacheProvider();
        }

        if ("none".equalsIgnoreCase(value)) {
            return new DefaultCacheProvider();
        }

        return (CacheProvider) Class.forName(value).newInstance();
    }

    /**
     * 获取缓存配置
     *
     * @param props
     * @param provider
     * @return
     */
    private final static Properties getProviderProperties(Properties props, CacheProvider provider) {
        Properties new_props = new Properties();
        Enumeration<Object> keys = props.keys();
        String prefix = provider.name() + '.';
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            if (key.startsWith(prefix))
                new_props.setProperty(key.substring(prefix.length()), props.getProperty(key));
        }
        return new_props;
    }

    /**
     * 建立缓存
     *
     * @param level
     * @param cache_name
     * @param autoCreate
     * @return
     */
    private final static Cache buildCache(int level, String cache_name, boolean autoCreate) {
        return ((level == 1) ? l1_provider : l2_provider).buildCache(cache_name, autoCreate, listener);
    }

    /**
     * 停止缓存
     *
     * @param level
     */
    public final static void shutdown(int level) {
        ((level == 1) ? l1_provider : l2_provider).stop();
    }

    /**
     * 获取缓存中的数据
     *
     * @param level Cache Level: L1 and L2
     * @param name  Cache region name
     * @param key   Cache key
     * @return Cache object
     */
    public final static Object get(int level, String name, Object key) {
        if (name != null && key != null) {
            Cache cache = buildCache(level, name, false);
            if (cache != null)
                return cache.get(key);
        }
        return null;
    }

    /**
     * 获取缓存中的数据
     *
     * @param level       Cache Level -&gt; L1 and L2
     * @param resultClass Cache object class
     * @param name        Cache region name
     * @param key         Cache key
     * @return Cache object
     */
    @SuppressWarnings("unchecked")
    public final static <T> T get(int level, Class<T> resultClass, String name, Object key) {
        if (name != null && key != null) {
            Cache cache = buildCache(level, name, false);
            if (cache != null)
                return (T) cache.get(key);
        }
        return null;
    }

    /**
     * 写入缓存
     *
     * @param level Cache Level: L1 and L2
     * @param name  Cache region name
     * @param key   Cache key
     * @param value Cache value
     */
    public final static void set(int level, String name, Object key, Object value) {
        if (name != null && key != null && value != null) {
            Cache cache = buildCache(level, name, true);
            if (cache != null)
                cache.put(key, value);
        }
    }

    public final static void set(int level, String name, Object key, Object value, Integer expireInSec) {
        if (name != null && key != null && value != null) {
            Cache cache = buildCache(level, name, true);
            if (cache != null)
                cache.put(key, value, expireInSec);
        }
    }

    /**
     * 清除缓存中的某个数据
     *
     * @param level Cache Level: L1 and L2
     * @param name  Cache region name
     * @param key   Cache key
     */
    public final static void evict(int level, String name, Object key) {
        if (name != null && key != null) {
            Cache cache = buildCache(level, name, false);
            if (cache != null)
                cache.evict(key);
        }
    }

    /**
     * 批量删除缓存中的一些数据
     *
     * @param level Cache Level： L1 and L2
     * @param name  Cache region name
     * @param keys  Cache keys
     */
    @SuppressWarnings("rawtypes")
    public final static void batchEvict(int level, String name, List keys) {
        if (name != null && keys != null && keys.size() > 0) {
            Cache cache = buildCache(level, name, false);
            if (cache != null)
                cache.evict(keys);
        }
    }

    /**
     * Clear the cache
     *
     * @param level Cache level
     * @param name  cache region name
     */
    public final static void clear(int level, String name) throws CacheException {
        Cache cache = buildCache(level, name, false);
        if (cache != null)
            cache.clear();
    }

    /**
     * list cache keys
     *
     * @param level Cache level
     * @param name  cache region name
     * @return Key List
     */
    @SuppressWarnings("rawtypes")
    public final static List keys(int level, String name) throws CacheException {
        Cache cache = buildCache(level, name, false);
        return (cache != null) ? cache.keys() : null;
    }

}
