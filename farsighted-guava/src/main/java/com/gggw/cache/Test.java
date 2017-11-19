package com.gggw.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.security.Key;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by cuigaowei on 2017/9/25.
 */
public class Test {

    static LoadingCache<String, String>  cahceBuilder = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .build(
                    new CacheLoader<String, String>() {
                        @Override
                        public String load(String s) throws Exception {
                            return createExpensiveGraph(s);
                        }
                    }
            );

    static Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(3000, TimeUnit.MILLISECONDS)
            .build();


    private static String createExpensiveGraph(String s) {
        return "hello" + s;
    }

    public static void main(String[] args) throws Exception {
        cahceBuilder.put("name", "jerry");
        cahceBuilder.put("age", "18");
        System.out.println("name: " + cahceBuilder.get("name"));
        System.out.println("age: " + cahceBuilder.get("age"));

        cache.put("jerry", "setJerry");

        String resultVal = cache.get("jerry", new Callable<String>() {
            public String call() {
                String strProValue="hello "+"jerry"+"!";
                return strProValue;
            }
        });

        System.out.println(resultVal);

        Thread.sleep(4000);

        resultVal = cache.get("jerry", new Callable<String>() {
            public String call() {
                String strProValue="hello "+"jerry"+"!";
                return strProValue;
            }
        });

        System.out.println(resultVal);
    }
}
