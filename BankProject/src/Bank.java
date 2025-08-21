import java.util.*;

public class Bank {
    private static Integer ID = 10001;
    private HashMap<Integer, BankAccount> accounts = new HashMap<>();
    private Scanner input = new Scanner(System.in);


    public BankAccount CreateAccount(String PIN, String HolderName, double initialBalance) {
        BankAccount z = null;// لازم يتعرف برة البلوك خلي بالك

        do {
            System.out.println("Please enter the type of your account");
            System.out.println("1. Checking Account");
            System.out.println("2. Saving Account");
            int x = input.nextInt();

            if (x == 1) {
                z = new checkingAccount(initialBalance, HolderName, PIN);
                z.addTransaction(initialBalance);
            } else if (x == 2) {
                z = new savingAccount(initialBalance, HolderName, PIN);
                z.addTransaction(initialBalance);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (z == null);

        accounts.put(ID, z);
        ID++;
        return z;
    }



    // عرض كل الحسابات
    public void getAllAccounts() {
        for (BankAccount account : accounts.values()) {
            System.out.println(account);
        }
    }





    public BankAccount Authenticate(String PIN) {
        for (BankAccount account : accounts.values()) {
            if (account.getPinHash().equals(PIN)) {
                return account;
            }
        }
        return null;
    }



    public void sortAccounts() {
        System.out.println("Choose sorting criteria: ");
        System.out.println("1. Balance");
        System.out.println("2. Account Holder Name");
        System.out.print("Enter choice: ");
        int choice = input.nextInt();

        String criteria;
        if (choice == 1) {
            criteria = "balance";
        } else if (choice == 2) {
            criteria = "name";
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        List<BankAccount> accountList = new ArrayList<>(accounts.values());
        mergeSort(accountList, 0, accountList.size() - 1, criteria);

        System.out.println("\nSorted accounts by " + criteria + ":");
        for (BankAccount account : accountList) {
            System.out.println(account);
        }
    }

    // --- MergeSort ---
    private void mergeSort(List<BankAccount> list, int left, int right, String criteria) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid, criteria);
            mergeSort(list, mid + 1, right, criteria);
            merge(list, left, mid, right, criteria);
        }
    }

    private void merge(List<BankAccount> list, int left, int mid, int right, String criteria) {
        List<BankAccount> temp = new ArrayList<>();
        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            boolean condition;
            if (criteria.equals("balance")) {
                condition = list.get(i).getBalance() <= list.get(j).getBalance();
            } else { // "name"
                condition = list.get(i).getAccountHolderName()
                        .compareTo(list.get(j).getAccountHolderName()) <= 0;
            }

            if (condition) {
                temp.add(list.get(i));
                i++;
            } else {
                temp.add(list.get(j));
                j++;
            }
        }

        while (i <= mid) {
            temp.add(list.get(i++));
        }

        while (j <= right) {
            temp.add(list.get(j++));
        }

        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }
}
