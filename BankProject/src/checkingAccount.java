import java.time.LocalDateTime;

public class checkingAccount extends BankAccount {
    private final double limit = 7000;

    public checkingAccount(double balance, String accountHolderName, String pinHash) {
        super(balance, accountHolderName, pinHash);
    }


    @Override
    public void withdraw(double amount) {

        if (amount <= limit && amount <= balance) {

           addTransaction(amount*-1);


        } else {
            System.out.println("Insufficient balance");
        }
    }














}
