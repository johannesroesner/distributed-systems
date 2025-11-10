import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    private int capacity;
    private Deque<Product> list = new LinkedList<>();
    private Lock monitor = new ReentrantLock();
    private Condition writer = monitor.newCondition();
    private Condition reader = monitor.newCondition();

    public Storage(int capacity){
        this.capacity = capacity;
    }


    public void add(Product newProduct){
        monitor.lock();
        try{
            while(list.size() == capacity){
                try{
                    System.out.println("    no space for " + newProduct.getId() + " left.");
                    writer.await();
                } catch(Exception e){}
            }
            list.addLast(newProduct);
            System.out.println(newProduct.getId() + " added.");
            reader.signalAll();
        } finally{
            monitor.unlock();
        }
    }

    public void consume(String consumerName){
        monitor.lock();
        try{
            while(list.size() == 0){
                try{
                    System.out.println("    no product for " + consumerName + " left");
                    reader.await();
                } catch(Exception e){}
            }

            Product p = list.removeFirst();
            System.out.println(p.getId() + " consumed from " + consumerName);
            writer.signalAll();
        } finally{
            monitor.unlock();
        }
    }
}