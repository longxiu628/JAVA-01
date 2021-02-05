package day3.week04;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 利用ExecutorService.submit(new CallableA());方法
 */
public class ExecuteServiceDemoB {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new CallableA());
        executorService.shutdown();
        try {
            System.out.println("返回结果:"+ future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
