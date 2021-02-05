package day3.week04;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 利用ExecutorService.submit(new RunnableB());方法
 */
public class ExecuteServiceDemoA {

    public static void main(String[] args) {
        //此处当Person 改为String时,获取不到值
        Person person = new Person();
//        ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1,
//                10L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>());
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Person> future = executorService.submit(new RunnableB(person),person);

        System.out.println("future.isDone():"+future.isDone());
        System.out.println("返回结果:"+ JSON.toJSONString(person));
        executorService.shutdown();
    }
}
