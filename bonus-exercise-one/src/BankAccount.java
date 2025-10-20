public class BankAccount {
    private int balance;
    private Object monitor = new Object();

    public BankAccount(int balance){
        this.balance = balance;
    }

    public void bookVacation(){
        synchronized(monitor){
            try{
                while(balance <= 500){
                    monitor.wait();
                }
            }catch(Exception e){}
            System.out.println("----------vacation booked----------");
            System.exit(0);
        }
    }

    public void deposit(int value){
        synchronized(monitor){
            balance += value;
            System.out.println("payment received");
            System.out.println("-----balance: " + balance);
            monitor.notifyAll();
        }
    }

    public void withdraw(String issuer,int value){
        synchronized(monitor){
            try{
                while(balance - value < 0){
                    System.out.println("bill from " + issuer + " with the value " + value + " can't be payed");
                    System.out.println("------balance: " + balance);
                    monitor.wait();
                }
            }catch(Exception e){}
            balance -= value;
            System.out.println("bill from " + issuer + " with the value " + value + " is payed");
            System.out.println("------balance: " + balance);
            monitor.notifyAll();
        }
    }
}