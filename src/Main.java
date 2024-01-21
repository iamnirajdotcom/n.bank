import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.*;

public class Main {

    public static void main(String[] args){

        // stage 1 development

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
        String filepath = "C:\\Users\\nkg\\IdeaProjects\\Banking\\src\\data\\database.csv";
        createFile(filepath);
        Scanner sc = new Scanner(System.in);

        boolean keepRunning = true;

        while(keepRunning){
            System.out.println("Press 0 for Old customer and 1 for new customer and 5 to exit the program");
            int userInput = sc.nextInt();
            sc.nextLine();
            if(userInput==5){
                keepRunning = false;
            }else if (userInput==0){
                // old customer can credit, debit and read account detail
                System.out.print("PLease enter your account number");
                int oldUserAccountNo = sc.nextInt();
                // take the account number and search for the account with similar number
                Account oldUserAccount = searchForAccount(filepath,oldUserAccountNo);

                if (oldUserAccount == null){
                    System.out.println("Not a valid account number");
                    continue;
                }
                System.out.println("Welcome to central bank of india " + oldUserAccount.getName() +"." );

                //now ask whether credit the money or debit the money or to view the Summary
                System.out.println("To credit press 2, for debit press 3 and to view account summary press 4");
                int oldUserInput = sc.nextInt();

                if(oldUserInput == 2){
                    System.out.println("Please enter the amount to be credited.");
                    int amountCredited = sc.nextInt();
                    oldUserAccount.creditMoney(amountCredited);
                    
                    // print the new total amount for the person and restart the loop
                    System.out.println("Net remaining balance "+oldUserAccount.getTotalBalance());
                    System.out.println("thank you for using our banking services");

                    // money is debited, but we need to update the file also
                    updateFile(filepath, oldUserAccountNo, oldUserAccount.getTotalBalance());

                } else if (oldUserInput == 3){
                    System.out.println("Please enter the amount to be debited.");
                    int amountDebited = sc.nextInt();
                    oldUserAccount.debitMoney(amountDebited);
                    
                    // print the new total amount for the person and restart the loop
                    System.out.println("Net remaining balance "+oldUserAccount.getTotalBalance());
                    System.out.println("thank you for using our banking services");

                    // money is debited, but we need to update the file also
                    updateFile(filepath, oldUserAccountNo, oldUserAccount.getTotalBalance());
                    

                } else if(oldUserInput==4){
                    // we need to print the account summary
                    oldUserAccount.accountSummary();
                    System.out.println("thank you for using our banking services");
                    
                }
            }else if (userInput==1){

                // for new customer we need to ask for there name and the capital they want to use to open the account
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Hi, there we are happy that you decided to open the bank account with us");
                System.out.println("--------------------------------------------------------------------------");

                System.out.println("Please enter your name");
                String newUserName = sc.nextLine();
                System.out.println("Please enter the amount with which you want to open an account with");
                int newUserInitialCapital = sc.nextInt();

                // we have the name and the initial capital now create an account and return the account no
                Account account = new Account(newUserName, newUserInitialCapital);
                int newUserAccountNo = account.getAccountNumber();
                System.out.println("Hello "+ newUserName+" your account number is " + newUserAccountNo);

                // now we need to  write this data inside the document 
                // so collect teh data
                String accountDetail = newUserAccountNo + "," + newUserName + "," +newUserInitialCapital;
                // first we need to make a list of string to be saved in the object
                List<String> data = new ArrayList<>();
                data.add(accountDetail);
                writeInsideFile(filepath, data);

                // now our data is saved so tell him thank you
                System.out.println("Thank you for opening the bank account with us");

            }
        }

    }

    private static void updateFile(String filepath, int accountNo, int newBalance) {
        Path path = Paths.get(filepath);
        try {
            List<String> lines = Files.readAllLines(path);
            // parse karo and change that particular line
            int lineNoTChange = 0;
            for(String line : lines){

                String[] st = line.split(",");
                if(Integer.parseInt(st[0])==accountNo){
                    // we found the line in lines
                    // make changes in lines
                    String newContent = st[0]+","+st[1]+","+newBalance;
                    lines.set(lineNoTChange,newContent);
                }
                lineNoTChange++;
            }

            Files.write(path,lines);

        }
        catch( Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static Account searchForAccount(String filepath, int oldUserAccountNo) {
        // lest write program to search for the account from the document
        // load the data from the file into a List
        List<Account> accounts  = readTheFile(filepath);

        for(Account data: accounts){
            if(oldUserAccountNo == data.getAccountNumber()){
                return data;
            }
        }
        return null;
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

    private static List<Account> readTheFile(String filepath) {
        Path path = Paths.get(filepath);
        List<Account> accounts = new ArrayList<>();

        try{
            List<String> lines = Files.readAllLines(path);
            for(String line : lines){
                String[] details = line.split(",");
                Account account = new Account(Integer.parseInt(details[0]),details[1],Integer.parseInt(details[2]));
                accounts.add(account);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return accounts;
    }

    private static void createFile(String filepath) {
        Path path = Paths.get(filepath);

        try {
            Files.createFile(path);

        } catch (Exception e){
            System.out.println("File is created");
            System.out.println(e.getMessage());
        }
    }

    // Method to find account from the Database
//    public static Account findAccount(List<Account> BankDatabase, int accountNo ){
//        for(Account account : BankDatabase){
//            if(account.getAccountNumber() == accountNo){
//                return account;
//            }
//        }
//        return null;
//    }
}
