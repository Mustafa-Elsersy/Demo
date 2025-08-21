import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class BankAccount {

    public double balance;
    protected String accountHolderName;
    protected String accountNumber;

    protected String pinHash;
    LinkedList<transaction> transactions = new LinkedList<>();
    private Scanner input = new Scanner(System.in);
    private  static long accountNoSetter=100000001L; //We use L to make it as an Integer here

    public BankAccount(double balance, String accountHolderName,  String pinHash) {
        this.balance = balance;
        this.accountHolderName = accountHolderName;
        this.accountNumber = String.valueOf(accountNoSetter);
        this.pinHash = pinHash;
        accountNoSetter ++;
    }

    public void deposit(double amount) {

     addTransaction(amount);

    }

    public void withdraw(double amount) {

        if (getBalance()<amount) {
         addTransaction(amount*-1);


        } else {
            System.out.println("Insufficient balance");
        }
    }


    public void addTransaction(double amount) {



        transaction T = new transaction();
        T.setAmount(amount * -1);
        T.setTransactionDate(LocalDateTime.now());
        transactions.add(T);

    }


    public void getTransaction() {




        for (transaction T : transactions) {
            System.out.println(T);
        }
    }


    public double getBalance() {

        for (transaction T : transactions) {

            balance += T.getAmount();


        }
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber() {


            accountNumber = String.valueOf(accountNoSetter);
           accountNoSetter++;

    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public LinkedList<transaction> getTransactions() {
        return transactions;
    }

    public void setPinHash(int pin) {
        while (pin > 9999 && pin < 1000) {

            System.out.println("Pin is invalid please enter it again");
            pin = input.nextInt();

        }

        pinHash = String.valueOf(pin);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(accountNumber)
                .append("  ")
                .append(accountHolderName)
                .append("  ")
                .append(balance)
                .append("\n");

        // Each transaction on a new line
        for (transaction T : transactions) {
            sb.append(T.toString()).append("\n");
        }

        return sb.toString();
    }

    public String getPinHash() {
        return pinHash;
    }

}
