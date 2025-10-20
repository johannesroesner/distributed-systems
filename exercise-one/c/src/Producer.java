import java.util.Random;

public class Producer implements Runnable {
    private Storage storage;

    public Producer(Storage storage){
        this.storage = storage;
    }

    public void run(){
        while(true){
            try{
                Thread.sleep(500);
                storage.add(new Product("id " + new Random().nextInt(1000)));
            } catch(Exception e){}
        }
    }
}