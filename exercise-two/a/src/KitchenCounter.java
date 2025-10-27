import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KitchenCounter {
    private int counter;
    private int capacity;

    private Lock monitor = new ReentrantLock();
    private Condition student = monitor.newCondition();
    private Condition cook = monitor.newCondition();

    public KitchenCounter(int capacity){
        this.capacity = capacity;
    }

    public void put(String name){
        try {
            monitor.lock();
            while(counter == capacity){
                try{
                    System.out.println(name + ": kitchen counter at max capacity = " + capacity);
                    cook.await();
                }catch (Exception e){}
            }
            counter++;
            System.out.println(name + ": " + counter + " burgers ready");
            student.signal();
        } finally{
            monitor.unlock();
        }
    }

    public void take(String name){
        try{
            monitor.lock();
            while( counter == 0){
                try{
                    System.out.println(name + " " + "no burgers left");
                    student.await();
                } catch(Exception e){}
            }
            counter--;
            System.out.println(name + " " + counter + " burgers left");
            cook.signal();
        } finally{
            monitor.unlock();
        }
    }

}