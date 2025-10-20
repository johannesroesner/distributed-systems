import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ParkingGarage {
    private int availableParkingSpace;
    private int parkingSpace;
    private Lock monitor = new ReentrantLock();
    Condition parker = monitor.newCondition();
    Condition leaver = monitor.newCondition();

    public ParkingGarage(int parkingSpace){
        this.parkingSpace = parkingSpace;
        this.availableParkingSpace = parkingSpace;
    }

    public void driveIn(String carName){
        monitor.lock();
        try{
            while(availableParkingSpace == 0){
                try{
                    System.out.println("    no space: " + carName + " is waiting");
                    parker.await();
                } catch(Exception e){}
            }
            availableParkingSpace--;
            leaver.signalAll();
            System.out.println(carName + " parked.");
        } finally{
            monitor.unlock();
        }
    }

    public void driveOut(String carName){
        monitor.lock();
        try{
            while(availableParkingSpace == parkingSpace - 2){
                try{
                    System.out.println("    " + carName + " can't leave");
                    leaver.await();
                } catch(Exception e){}
            }
            availableParkingSpace++;
            System.out.println(carName + " left.");
            parker.signalAll();
        } finally{
            monitor.unlock();
        }
    }
}