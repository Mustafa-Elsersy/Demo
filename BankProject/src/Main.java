import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Bank bank = new Bank();

        while (true) {
            clear();
            System.out.println("============ Welcome to XYZ Bank System ============");
            System.out.println("1. Create New Account");
            System.out.println("2. Login to Existing Account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt();

            if (choice == 1) {
                createNewAccount(bank);
            } else if (choice == 2) {
                loginFlow(bank);
            } else if (choice == 3) {
                System.out.println("Goodbye!");
                return;
            } else {
                System.out.println("Invalid choice.");
                pause();
            }
        }
    }



    private static void createNewAccount(Bank bank) {
        clear();
        System.out.println("--- Create New Account ---");

        System.out.print("--- Enter account holder name: ");
        String holder = readLine();

        System.out.print("Set a 4-digit PIN: ");
        String pin = readLine();

        System.out.print("Initial deposit amount: ");
        double initial = readDouble();

        // Your Bank.CreateAccount asks the user again for account type (1/2) – we keep it.
        BankAccount acc = bank.CreateAccount(pin, holder, initial);

        System.out.println("Account created successfully!");
        System.out.println("Your account number: " + acc.getAccountNumber());
        pause();
    }

    private static void loginFlow(Bank bank) {
        clear();
        System.out.println("--- Login ---");

        System.out.print("Enter account number: ");
        String accNo = readLine();

        System.out.print("Enter PIN: ");
        String pin = readLine();

        // Your Authenticate returns an account by PIN; we also check the account number matches.
        BankAccount acc = bank.Authenticate(pin);
        if (acc == null || !acc.getAccountNumber().equals(accNo)) {
            System.out.println("Login failed. Wrong account number or PIN.");
            pause();
            return;
        }

        System.out.println("Login successful! Welcome, " + acc.getAccountHolderName() + ".");
        pause();

        boolean loggedIn = true;
        while (loggedIn) {
            clear();
            System.out.println("--- Main Menu ---");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. View Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Sort & View All Accounts (Admin Only)");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int ch = readInt();

            switch (ch) {
                case 1: // deposit
                    System.out.print("\nEnter amount to deposit: ");
                    double d = readDouble();
                    acc.deposit(d);
                    System.out.println("Deposit successful! Current balance: " + acc.getBalance());
                    pause();
                    break;

                case 2: // withdraw
                    System.out.print("\nEnter amount to withdraw: ");
                    double w = readDouble();
                    acc.withdraw(w);
                    System.out.println("Current balance: " + acc.getBalance());
                    pause();
                    break;

                case 3: // balance
                    System.out.println("\nCurrent balance: " + acc.getBalance());
                    pause();
                    break;

                case 4: // transactions
                    System.out.println("\n--- Transaction History ---");
                    System.out.println("Amount    Date & Time");
                    acc.getTransaction(); // your method prints each transaction
                    pause();
                    break;


                case 5:
                    bank.sortAccounts(); // uses your merge sort
                    pause();
                    break;

                case 6:
                    loggedIn = false;
                    System.out.println("Logged out.");
                    pause();
                    break;

                default:
                    System.out.println("Invalid choice.");
                    pause();
            }
        }
    }

    /* ---------------- Helpers ---------------- */

    public static int readInt() {
        while (!input.hasNextInt()) {
            System.out.print("Invalid number. Try again: ");
            input.next();
        }
        int v = input.nextInt();
        input.nextLine(); // consume newline
        return v;
    }

  public static double readDouble() {
        while (!input.hasNextDouble()) {
            System.out.print("Invalid number. Try again: ");
            input.next();
        }
        double v = input.nextDouble();
        input.nextLine(); // consume newline
        return v;
    }

   public static String readLine() {
        String s = input.nextLine();
        while (s.trim().isEmpty()) s = input.nextLine();
        return s.trim();
    }

  public static void pause() {
        System.out.print("\nPress Enter to continue...");
        input.nextLine();
    }

   public static void clear() {
        System.out.println();
    }
}
