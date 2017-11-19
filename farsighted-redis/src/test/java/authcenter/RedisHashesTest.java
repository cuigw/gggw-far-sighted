package authcenter;

import authcenter.base.BaseTest;
import org.junit.Test;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cui.GaoWei
 * @date 2017/11/10
 */

public class RedisHashesTest extends BaseTest {

    @Resource(name="authRedisTemplate")
    private RedisTemplate redisTemplate;

    @Resource(name="authStringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    private ValueOperations<String, String> valueOperations;
    private HashOperations<String, Integer, Object> hashOperations;

    public void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Test
    public void testHahes() {

        init();

        Map<Integer,Object> testMap = new HashMap();
        testMap.put(1,"/url/add");
        testMap.put(2,"/url/del");
        testMap.put(3,"/url/get");
        //全部设置
        hashOperations.putAll("redisHashTest", testMap);
        sout();

        //删除
        hashOperations.delete("redisHashTest", 1);
        System.out.println(hashOperations.get("redisHashTest", 2));

        sout();

        //设置单个
        hashOperations.put("redisHashTest", 4, "/url/4");
        sout();

        //批量获取
        List<Integer> kes = new ArrayList<Integer>();
        kes.add(3);
        kes.add(2);
        System.out.println(hashOperations.multiGet("redisHashTest", kes));

        //keys 获取散列中所有键
        System.out.println(hashOperations.keys("redisHashTest"));

        //Boolean putIfAbsent(H key, HK hashKey, HV value);仅当hashKey不存在时才设置散列hashKey的值。
    }

    private void sout() {
        System.out.println(hashOperations.entries("redisHashTest"));
    }

}
