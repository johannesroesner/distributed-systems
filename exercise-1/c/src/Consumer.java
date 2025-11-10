import java.util.Random;

public class Consumer implements Runnable {
    private String name;
    private Storage storage;

    public Consumer(String name, Storage storage){
        this.name = name;
        this.storage = storage;
    }

    public void run(){
        while(true){
            try{
                Thread.sleep(new Random().nextInt(11) * 1000);
                storage.consume(name);
            } catch(Exception e){}
        }
    }
}