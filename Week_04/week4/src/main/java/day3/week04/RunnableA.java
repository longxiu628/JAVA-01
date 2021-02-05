package day3.week04;

public class RunnableA implements Runnable{

    public static String str = "";

    public RunnableA(String str) {
        super();
        this.str = str;
    }

    @Override
    public void run() {
        str = "ABC";
    }

}
