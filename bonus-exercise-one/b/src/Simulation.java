public class Simulation {
    public static void main(String[] args) throws Exception{

        System.out.println("----------starting simulation----------");

        MyCountDownLatch latch = new MyCountDownLatch(3);

        new Thread(new TestRunner("A", latch)).start();
        new Thread(new TestRunner("B", latch)).start();

        latch.countDown();
        latch.countDown();
        //latch.countDown();
    }
}