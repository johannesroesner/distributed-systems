import java.util.Random;

public class Student implements Runnable {
    private String name;
    private KitchenCounter kitchenCounter;

    public Student(String name, KitchenCounter kitchenCounter){
        this.name = name;
        this.kitchenCounter = kitchenCounter;
    }

    public void run(){
        while(true){
            try{
                Thread.sleep(new Random().nextInt(11) * 1000);
                kitchenCounter.take(name);
            } catch(Exception e){}
        }
    }
}