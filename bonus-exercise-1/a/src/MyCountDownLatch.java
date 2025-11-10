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

    public synchronized void countDown(){
        threshold--;
        if(threshold == 0){
            notifyAll();
        }
    }
}