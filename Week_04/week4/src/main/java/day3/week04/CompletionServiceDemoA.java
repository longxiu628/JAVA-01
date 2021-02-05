package day3.week04;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.*;

/**
 * 利用CompletionService
 */
public class CompletionServiceDemoA {

    public static void main(String[] args) {
        Person person = new Person();
        Callable callable1 = new CallableB(person);
        Callable callable2 = new CallableB(person);
        Callable callable3 = new CallableB(person);
        Callable callable4 = new CallableB(person);

        Executor executor = Executors.newCachedThreadPool();
        CompletionService completionService = new ExecutorCompletionService(executor);
        completionService.submit(callable1);
        completionService.submit(callable2);
        completionService.submit(callable3);
        completionService.submit(callable4);
        try {
            for (int i = 0; i <5 ; i++) {
                Future future = completionService.poll(1,TimeUnit.SECONDS);
                System.out.println("返回结果:"+ JSON.toJSONString(future.get()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
