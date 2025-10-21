public class VacationBooker implements Runnable {
    private BankAccount bankAccount;

    public VacationBooker(BankAccount bankAccount){
        this.bankAccount = bankAccount;
    }

    public void run(){
        try{
            while(true){
                Thread.sleep(2000);
                bankAccount.bookVacation();
            }
        }catch(Exception e){}
    }
}