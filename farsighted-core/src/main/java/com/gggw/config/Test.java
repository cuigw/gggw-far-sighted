package com.gggw.config;

import org.aeonbits.owner.ConfigFactory;

/**
 * Created by cuigaowei on 2017/6/30.
 */
public class Test {
    public static void main(String[] args) {
        DBConfig cfg = ConfigFactory.create(DBConfig.class);
        System.out.println("Server " + cfg.dbDriverClassName());
    }
}
