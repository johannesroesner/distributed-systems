public class Simulation {
    public static void main(String[] args) throws Exception{
        ParkingGarage parkingGarage = new ParkingGarage(10);

        System.out.println("----------starting simulation----------");

        for(int i = 1; i <= 20; i++) {
            Thread t = new Thread(new Car("DEG JR " + i, parkingGarage));
            t.setDaemon(true);
            t.start();
        }

        Thread.sleep(30000);
        System.out.println("----------closing simulation----------");
    }
}