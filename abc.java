import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class abc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = new BankAccount();
        ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
        File file = new File("data.bin");
        int loopVar = 1;
        try {
            accounts = BankAccount.readObjectFromFile(file);
            while (loopVar == 1) {
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println("Total number of accounts: " + accounts.size());
                System.out.println("1> create a new account\n2> Open an existing account\n3> Exit");
                int ch = sc.nextInt();
                if (ch == 1) {
                    account.createAccount();
                    accounts.add(account);
                    BankAccount.writeObjectToFile(accounts, file);
                } else if (ch == 2) {
                    System.out.println("Enter the account number: ");
                    int accNo = sc.nextInt();
                    account = null;
                    for (int i = 0; i < accounts.size(); i++) {
                        if (accounts.get(i).accNo == accNo) {
                            account = accounts.get(i);
                            break;
                        }
                    }
                    if (account == null) {
                        System.out.println("Account not found");
                    } else {
                        int x = 1;
                        while (x == 1) {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Select an action");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. View Details");
                            System.out.println("4. go to main menu");
                            // sc.nextLine();
                            int choice = sc.nextInt();
                            switch (choice) {
                                case 1:
                                    account.deposit();
                                    updateList(accounts, account);
                                    BankAccount.writeObjectToFile(accounts, file);
                                    sc.nextLine();
                                    sc.nextLine();
                                    break;
                                case 2:
                                    account.withdraw();
                                    updateList(accounts, account);
                                    BankAccount.writeObjectToFile(accounts, file);
                                    sc.nextLine();
                                    sc.nextLine();
                                    break;
                                case 3:
                                    account.viewDetails();
                                    sc.nextLine();
                                    sc.nextLine();
                                    break;
                                case 4:
                                    x = 0;
                                    break;

                                default:
                                    System.out.println("Choose from the menu");
                                    sc.nextLine();
                                    sc.nextLine();
                                    break;
                            }

                        }
                    }

                } else if (ch == 3) {
                    loopVar = 0;
                } else {
                    System.out.println("Choose from the menu");
                }
            }

        } catch (IOException e) {
            System.out.println("something went wrong");
            e.printStackTrace();
        }
        sc.close();
    }

    public static void updateList(ArrayList<BankAccount> accounts, BankAccount account) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).accNo == account.accNo) {
                accounts.set(i, account);
                break;
            }
        }
    }

}
