import java.util.Random;

public class Cook implements Runnable {
    private String name;
    private KitchenCounter kitchenCounter;

    public Cook(String name, KitchenCounter kitchenCounter){
        this.name = name;
        this.kitchenCounter = kitchenCounter;
    }

    public void run(){
        while(true){
            try{
                Thread.sleep(new Random().nextInt(11) * 1000);
                kitchenCounter.put(name);
            } catch(Exception e){}
        }
    }
}