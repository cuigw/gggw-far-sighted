package authcenter;

import authcenter.base.BaseTest;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Cui.GaoWei
 * @date 2017/11/10
 */

public class RestSetsTest extends BaseTest {

    @Resource(name="authRedisTemplate")
    private RedisTemplate redisTemplate;

    @Resource(name="authStringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    private ValueOperations<String, String> valueOperations;

    public void init() {
        valueOperations = redisTemplate.opsForValue();
    }

    @Test
    public void testRestSet() {
        init();
        String a = "auth_url_";
        List keys = new ArrayList();

        long startSet = System.currentTimeMillis();

        for(int i = 0; i < 2000; i++) {
            String temp = a + i;
            String tempValue = temp + "value";
            keys.add(temp);
            valueOperations.set(temp, tempValue, 1, TimeUnit.HOURS);
        }

        long endSet = System.currentTimeMillis() - startSet;

        System.out.println("set 2000 cost " + endSet + "ms");

        long start = System.currentTimeMillis();

        List list = stringRedisTemplate.opsForValue().multiGet(keys);

        long end = System.currentTimeMillis() - start;
        System.out.println("get 2000 cost " + end + "ms");
    }
}
