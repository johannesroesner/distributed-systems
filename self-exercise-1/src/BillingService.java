import java.util.Random;

public class BillingService implements Runnable{
    private String name;
    private BankAccount bankAccount;

    public BillingService(String name, BankAccount bankAccount){
        this.name = name;
        this.bankAccount = bankAccount;
    }

    public void run(){
        try{
            while(true){
                bankAccount.withdraw(name, new Random().nextInt(96));
                Thread.sleep(2000);
            }
        }catch(Exception e){}
    }
}