package com.gggw.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by cuigaowei on 2017/7/19.
 */
public class CacheUtil {


    public static Map createExpensiveGraph(String key) {
        Map map = new HashMap();
        map.put(key, "cgw");
        return map;
    }

    public static void main(String[] args) {
        LoadingCache<String, Map> graphs = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                //.removalListener(MY_LISTENER)
                .build(
                        new CacheLoader<String, Map>() {
                            public Map load(String key) throws Exception {
                                return createExpensiveGraph(key);
                            }
                        });
        try {
            System.out.println( graphs.get("10000"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
