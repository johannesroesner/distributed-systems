import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MyCountDownLatch {
    private int threshold;

    public MyCountDownLatch(int threshold){
        this.threshold = threshold;
    }

    public synchronized void await(){
        try{
            while(threshold > 0){
                wait();
            }
        } catch(Exception e){}
    }

    public synchronized boolean await(long timeout, TimeUnit unit) throws InterruptedException{
        final boolean onTime[] = {true};
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized (MyCountDownLatch.this) {
                    onTime[0] = false;
                    MyCountDownLatch.this.notifyAll();
                }
            }
        }, unit.toMillis(timeout));

        try{
            while(threshold > 0 && onTime[0]){
                wait();
            }
        } catch(Exception e){
            throw new InterruptedException();
        }

        return threshold == 0;
    }

    public synchronized void countDown(){
        threshold--;
        if(threshold == 0){
            notifyAll();
        }
    }
}