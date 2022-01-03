
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class BankAccount implements Serializable {

    // fields
    String name;
    int accNo;
    String accType = "Savings";
    double accBal = 0.0;

    public void createAccount() {

        System.out.println("Enter your name: ");
        Scanner sc = new Scanner(System.in);
        name = sc.nextLine();
        System.out.println("Enter your account number: ");
        accNo = sc.nextInt();
        System.out.println("Enter your account type: ");
        accType = sc.next();
        System.out.println("Enter your account balance: ");
        accBal = sc.nextDouble();
        System.out.println("Account created successfully");

    }

    public void deposit() {
        double deposit;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount to be deposited");

        deposit = sc.nextDouble();
        accBal += deposit;
        System.out.println("Your new account balance is " + accBal);

    }

    public void withdraw() {
        System.out.println("Enter the amount to be withdrawn: ");
        Scanner sc = new Scanner(System.in);
        double amt = sc.nextDouble();
        accBal -= amt;
        System.out.println("Withdrawl successfull...");
        System.out.println("Your new account balance is " + accBal);

    }

    public void viewDetails() {
        System.out.println("Account holder name: " + name);
        System.out.println("Account number: " + accNo);
        System.out.println("Account type: " + accType);
        System.out.println("Account balance: " + accBal);

    }

    public static void writeObjectToFile(ArrayList<BankAccount> obj, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(obj);
            oos.flush();
        } catch (IOException e) {
            System.out.println("Error in writing to file");

        }
    }

    public static ArrayList<BankAccount> readObjectFromFile(File file) {
        ArrayList<BankAccount> result = new ArrayList<BankAccount>();

        try (FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (ArrayList<BankAccount>) ois.readObject();

            return result;
        } catch (ClassNotFoundException | IOException e) {
            return result;

        }

    }

}
