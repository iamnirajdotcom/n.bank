import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.*;

public class Main {

    public static void main(String[] args){

//        System.out.println(">>>>> welcome to central bank of India <<<<<");
//        Scanner sc = new Scanner(System.in);
//        List<Account> bankDatabase = new ArrayList<Account>();
//
//        while(true){
//            System.out.println("-----------------------------------------------------------");
//            System.out.println("Old customer press 0 New customer press 1 To exit press 5 ");
//            System.out.println("-----------------------------------------------------------");
//            int userResponse = sc.nextInt();
//            sc.nextLine();
//            if(userResponse == 1){
//                System.out.println("please enter your name");
//                String name = sc.nextLine();
//                System.out.println("please enter the amount with which you want to start the account");
//                int totalBalance = sc.nextInt();
//                Account newUser = new Account(name, totalBalance);
//                System.out.println("Your account number is "+ newUser.accountNumber);
//                bankDatabase.add(newUser);
//                System.out.println("Thank you for opening bank account with us. Have a nice day");
//            }
//            else if (userResponse == 0) {
//                System.out.println("Welcome back 😊");
//                System.out.println("PLease enter your account number");
//                int oldUserAccountNo = sc.nextInt();
//
//                // now we need to search from the Bank database for a specific account number
//                Account account = findAccount(bankDatabase, oldUserAccountNo);
//                if(account == null){
//                    System.out.println("Sorry Bank Account with this Number does not exist. Please try again");
//                } else{
//                    System.out.println("Welcome back "+account.name );
//                    System.out.println("Press 2 to credit money and 3 to debit money and 4 to print account summary.");
//                    int oldUserResponse = sc.nextInt();
//                    if(oldUserResponse ==2){
//                        System.out.println("Enter the amount of money you want to credit");
//                        int creditAmount = sc.nextInt();
//                        account.creditMoney(creditAmount);
//                    } else if (oldUserResponse== 3) {
//                        System.out.println("Enter the amount of money you want to debit");
//                        int debitAmount = sc.nextInt();
//                        account.debitMoney(debitAmount);
//                    } else if(oldUserResponse == 4){
//                        System.out.println("Account Summary");
//                        account.accountSummary();
//                    }
//                }
//
//
//            }
//            else if( userResponse==5){
//                System.out.println("Thanks for using our service.");
//                break;
//            }
//
//        }

        // stage 2 development
        // using the Files class

        // file location
        String filepath = "C:\\Users\\nkg\\IdeaProjects\\LearnFileReading\\src\\data\\database.csv";

        // making a file
        createFile(filepath);

        // read a file
        readTheFile(filepath);

        //write in the file
        writeInsideFile(filepath,data);
        

        boolean keepRuning = true;

        while(keepRuning){
            

        }

    }

    private static void writeInsideFile(String filepath, List<String> data) {
        Path path = Paths.get(filepath);
        try{
            Files.write(path, data, StandardOpenOption.APPEND);
        }
        catch (Exception e){
            System.out.println();
        }
    }

    private static void readTheFile(String filepath) {
        Path path = Paths.get(filepath);

        try{
            List<String> lines = Files.readAllLines(path);
            for(String line : lines){
                String[] details = line.split(",");
                System.out.println("Account number" + details[0]+ ", Name " + details[1] + ", Bank Balance "+ details[2]);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void createFile(String filepath) {
        Path path = Paths.get(filepath);

        try {
            Files.createFile(path);
            System.out.println("File is created");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // Method to find account from the Database
    public static Account findAccount(List<Account> BankDatabase, int accountNo ){
        for(Account account : BankDatabase){
            if(account.getAccountNumber() == accountNo){
                return account;
            }
        }
        return null;
    }
}
