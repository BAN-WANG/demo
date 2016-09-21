package jedis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * RedisTemplate测试使用
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-redis.xml"})
public class RedisTemplateDemo {
    @Resource(name = "redisTemplate")
    private StringRedisTemplate redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String,String> valueOperations;

    @Test
    public void test(){
        ValueOperations<String,String> valueOperations= redisTemplate.opsForValue();
        valueOperations.set("123abc","test-测试-value");
        String value = valueOperations.get("123abc");
        System.out.println(value);
    }

    @Test
    public void test1(){
        String value = valueOperations.get("123abc");
        System.out.println("value=>"+value);
        Assert.assertTrue("test-测试-value".equals(value));
    }


}
