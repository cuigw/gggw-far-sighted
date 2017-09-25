package com.gggw.mongodb.base;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;

/**
 * Created by cuigaowei on 2017/8/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/*.xml"})
public class BaseTest {
    @Test
    public void preventError() {
        HashSet<String> sets = Sets.newHashSet();

    }
}
