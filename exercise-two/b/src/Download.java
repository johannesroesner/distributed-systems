import javax.swing.JProgressBar;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.Random;

public class Download implements Runnable {
    private int duration;
    private JProgressBar progressBar;
    private CountDownLatch countDownLatch;
    private CyclicBarrier cyclicBarrier;


    public Download(JProgressBar progressBar, CountDownLatch countDownLatch, CyclicBarrier cyclicBarrier){
        this.duration = new Random().nextInt(11) * 100;
        this.progressBar = progressBar;
        this.countDownLatch = countDownLatch;
        this.cyclicBarrier = cyclicBarrier;
    }

    public void run(){
        try{
            countDownLatch.await();
            try{
                for(int i = 0; i < 100; i++){
                    Thread.sleep(duration);
                    progressBar.setValue(i);
                }
            } catch(Exception e){}
            cyclicBarrier.await();
        } catch(Exception e){}
    }
}