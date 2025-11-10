import java.util.concurrent.TimeUnit;

public class TestRunner implements Runnable {
    private String name;
    private MyCountDownLatch latch;

    public TestRunner(String name, MyCountDownLatch latch){
        this.name = name;
        this.latch = latch;
    }

    public void run(){
        System.out.println(name + ": before latch");
        try{
            latch.await(10, TimeUnit.SECONDS);
        } catch(Exception e){}

        System.out.println(name + ": after latch");
    }
}