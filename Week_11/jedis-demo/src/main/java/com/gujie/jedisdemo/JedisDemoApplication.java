package com.gujie.jedisdemo;

import com.gujie.jedisdemo.service.JedisService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class JedisDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JedisDemoApplication.class, args);
        JedisService service = context.getAutowireCapableBeanFactory().getBean(JedisService.class);
        Runnable runnable = () -> {
            String lockId = null;
            while (lockId == null) {
                // 尝试获取锁
                lockId = service.occupyDistributedLock("dlock", 30000);
            }
            System.out.println(Thread.currentThread().getName() + "获取到分布式锁");
            // 生产订单
            service.produceOrder("订单" + lockId);
            // 计数器
            System.out.println(service.decrStock());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            service.releaseDistributedLock("dlock", lockId);
        };
        new Thread(runnable, "线程1").start();
        new Thread(runnable, "线程2").start();
    }

}
