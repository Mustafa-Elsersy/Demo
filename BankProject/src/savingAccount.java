public class savingAccount extends BankAccount{

    public  float interestRate;

    public savingAccount(double balance, String accountHolderName, String pinHash) {
        super(balance, accountHolderName, pinHash);
    }


    @Override

    public double getBalance() {
        return interestRate*balance+balance;
    }





    public void addInterest(float value){

        interestRate = value;



    }



}
