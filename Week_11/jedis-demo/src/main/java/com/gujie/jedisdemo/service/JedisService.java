package com.gujie.jedisdemo.service;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;
import java.util.UUID;

@Service
public class JedisService {

    JedisPool jedisPool = new JedisPool("localhost", 6379);

    public JedisService() {
        // 订单消费线程
        new Thread(()->{
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.subscribe(new JedisPubSub() {
                    @Override
                    public void onMessage(String channel, String message) {
                        super.onMessage(channel, message);
                        System.out.println("消费" + message);
                    }
                }, "channel1");
            }
        }, "subThread").start();
    }

    public String occupyDistributedLock(String lockKey, int expireTime) {
        //锁id（必须拥有此id才能释放锁）
        String lockId = UUID.randomUUID().toString();
        //占用锁同时设置失效时间
        SetParams setParams = new SetParams();
        setParams.nx();
        setParams.ex(expireTime);
        try (Jedis jedis = jedisPool.getResource()) {
            String result = jedis.set(lockKey, lockId, setParams);
            //占用锁成功返回锁id，否则返回null
            if("OK".equals(result)){
                return lockId;
            }
        }
        return null;
    }

    public void releaseDistributedLock(String lockKey,String lockId) {
        if(lockId != null){
            //执行Lua代码删除lockId匹配的锁
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(lockId));
            }
        }

    }

    public long decrStock() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.decr("count");
        }
    }

    public void produceOrder(String order) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.publish("channel1", order);
        }
    }
}
