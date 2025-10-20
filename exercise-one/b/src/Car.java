import java.util.Random;

public class Car implements Runnable {
    private String name;
    private ParkingGarage parkingGarage;

    public Car(String name, ParkingGarage parkingGarage){
        this.name = name;
        this.parkingGarage = parkingGarage;
    }

    public void run(){
        while(true){
            try{
                Thread.sleep( new Random().nextInt(11) * 1000);
                parkingGarage.driveIn(name);
                Thread.sleep( new Random().nextInt(11) * 1000);
                parkingGarage.driveOut(name);
            } catch(Exception e){}
        }
    }
}