public class Employer implements Runnable{
    private BankAccount bankAccount;

    public Employer(BankAccount bankAccount){
        this.bankAccount = bankAccount;
    }

    public void run(){
        try{
            while(true){
                bankAccount.deposit(100);
                Thread.sleep(2000);
            }
        }catch(Exception e){}
    }
}