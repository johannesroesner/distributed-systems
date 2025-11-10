public class TestRunner implements Runnable {
    private String name;
    private MyCountDownLatch latch;

    public TestRunner(String name, MyCountDownLatch latch){
        this.name = name;
        this.latch = latch;
    }

    public void run(){
        System.out.println(name + ": before latch");
        latch.await();
        System.out.println(name + ": after latch");
    }
}