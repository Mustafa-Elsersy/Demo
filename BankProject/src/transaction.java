import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class transaction {

    private String transactionDate; // stored as formatted string
    private double amount;          // better to store as number

    // Getter
    public String getTransactionDate() {
        return transactionDate;
    }

    // Setter that takes LocalDateTime and formats it
    public void setTransactionDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.transactionDate = dateTime.format(formatter);
    }

    // Getter and Setter for amount
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {

        return amount + "   " + transactionDate;
    }



}
