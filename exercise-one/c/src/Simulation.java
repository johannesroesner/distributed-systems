public class Simulation {
    public static void main(String[] args) throws Exception{
        Storage storage = new Storage(10);

        System.out.println("----------starting simulation----------");

        Thread p = new Thread(new Producer(storage));
        p.setDaemon(true);
        p.start();

        for(int i = 1; i <= 6; i++) {
            Thread c = new Thread(new Consumer("consumerId: " + i, storage));
            c.setDaemon(true);
            c.start();
        }

        Thread.sleep(30000);
        System.out.println("----------closing simulation----------");
    }
}