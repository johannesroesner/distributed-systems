import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ParkingGarage {
    private int availableParkingSpaces;
    private Lock monitor = new ReentrantLock();
    Condition parker = monitor.newCondition();

    public ParkingGarage(int availableParkingSpaces){
        this.availableParkingSpaces = availableParkingSpaces;
    }

    public void driveIn(String carName){
        monitor.lock();
        try{
            while(availableParkingSpaces == 0){
                try{
                System.out.println("    no space: " + carName + " is waiting");
                parker.await();
                } catch(Exception e){}
            }
            availableParkingSpaces--;
            System.out.println(carName + " parked.");
        } finally{
            monitor.unlock();
        }
    }

    public void driveOut(String carName){
        monitor.lock();
        try{
            availableParkingSpaces++;
            System.out.println(carName + " left.");
            parker.signalAll();
        } finally{
            monitor.unlock();
        }
    }

}