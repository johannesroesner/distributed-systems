public class Simulation {
    /*
    * Saving Simulator
    * In order to go on vacation, you want to save money.
    * You receive a salary of 100€ every month from your employer.
    * At the same time, you have to pay two bills each month, each between 0 and 95 euros (randomly generated).
    * If you cannot pay a bill, you must wait until there is enough money in the account.
    * As soon as you have 500 euros or more in your account, the vacation should be automatically booked.
    *
    * Data:
    * Classes: Simulation, BankAccount, Employer, BillingService, VacationBooker
    * Starting account balance = 42 euros
    * 1 month = 2 seconds = 2000 ms
    *
    * Note:
    * - The function `bookVacation()` should be implemented directly in the BankAccount class
    * - The VacationBooker should check once a month whether the vacation can be paid
    */

    public static void main (String[] args){
        BankAccount bankAccount = new BankAccount(42);

        new Thread(new Employer(bankAccount)).start();
        new Thread(new BillingService("A", bankAccount)).start();
        new Thread(new BillingService("B", bankAccount)).start();
        new Thread(new VacationBooker(bankAccount)).start();
       }
}