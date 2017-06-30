package com.gggw.config;

import org.aeonbits.owner.Config;

/**
 * Created by cuigaowei on 2017/6/30.
 */
@Config.Sources({ "file:~/Dev/runtime_config/db/db.properties",
        "file:/etc/runtime_config/db/db.properties",
        "classpath:runtime_config/db/db.propertiess" })
public interface DBConfig extends Config {
    @Key("db.url")
    String dbUrl();
    @Key("db.driverClassName")
    String dbDriverClassName();
    @Key("db.username")
    String dbUserNmae();
    @Key("db.password")
    String dbPassword();

}
