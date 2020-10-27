package redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.got.common.core.serializer.jackson.JacksonObjMapper;
import com.got.common.core.util.JacksonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.*;

/**
 * @ClassName: ZSetClient
 * @Description:
 * @Author 胡鹏
 * @Date 2020/10/9
 */
public class ZSetClient {

    private RedisTemplate<String, Object> redisTemplate;

    @Before
    public void test() {
        String host = "127.0.0.1";
        int port = 6379;
        String password = "root";

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(5);
        config.setMaxIdle(10);
        config.setMaxTotal(20);
        config.setMaxWaitMillis(5000);

        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
//        factory.setPassword(password);
        factory.setDatabase(0);
        factory.setPoolConfig(config);
        factory.afterPropertiesSet();

        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer(new JacksonObjMapper());

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(jsonSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(jsonSerializer);
        template.afterPropertiesSet();
        redisTemplate = template;
    }

    @Test
    public void zUnionStore() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(5);
        config.setMaxIdle(10);
        config.setMaxTotal(20);
        config.setMaxWaitMillis(5000);

        String masterName ="hp-master";
        Set<String> sentinels =new HashSet<String>();
        sentinels.add(new HostAndPort("192.168.92.131",26379).toString());
        sentinels.add(new HostAndPort("192.168.92.131",26380).toString());
        sentinels.add(new HostAndPort("192.168.92.131",26381).toString());
        JedisSentinelPool jedisSentinelPool =new JedisSentinelPool(masterName, sentinels, config,3000,null);

        jedisSentinelPool.getResource().set("key", "value");


//        Long count = redisTemplate.opsForZSet().unionAndStore("zset1", Arrays.asList("zset2", "zset3"), "zset");
//        System.out.println(count);
        Long result = redisTemplate.opsForList().leftPush("list", new User());
        System.out.println(result);
    }


}
