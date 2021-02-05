package day3.week04;

import java.util.concurrent.Callable;

public class CallableA implements Callable<String> {

    public String call() throws Exception {
        return "ABC";
    }
}
