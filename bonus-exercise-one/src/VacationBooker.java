public class VacationBooker implements Runnable {
    private BankAccount bankAccount;

    public VacationBooker(BankAccount BankAccount){
        this.bankAccount = bankAccount;
    }

    public void run(){
        try{
            while(true){
                bankAccount.bookVacation();
                Thread.sleep(2000);
            }
        }catch(Exception e){}
    }
}