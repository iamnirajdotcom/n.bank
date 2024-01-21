// 
import java.util.*;
public class Account {

    private int accountNumber;
    private String name ;
    private int totalBalance;

    Random random = new Random();
    public Account(String name , int totalBalance) {

        int min = 10000000;
        int max = 99999999;
        accountNumber = random.nextInt(max - min + 1) + min;
        this.name = name;
        this.totalBalance = totalBalance;
    }

    public Account(int accountNumber , String name, int totalBalance){
        this.accountNumber = accountNumber;
        this.name = name;
        this.totalBalance = totalBalance;
    }

    //getter and setter
     public void setName(String name){
        this.name = name;
     }

     public void setTotalBalance(int balance){
        this.totalBalance = balance;
     }

     public int getAccountNumber(){
        return accountNumber;
     }

     public String getName(){
        return name;
     }

     public int getTotalBalance(){
        return totalBalance;
     }


        //method 1
    // money credited
    public void creditMoney(int creditAmount){
        totalBalance = totalBalance+creditAmount;
        System.out.println("Amount " + creditAmount + " is "+"credited in your bank account"+
                            "Now your new Balance is "+ totalBalance);
    }

    //method 2
    // money debited
    public void debitMoney(int debitAmount){
        totalBalance = totalBalance-debitAmount;
        System.out.println("Amount " + debitAmount + " is "+"credited in your bank account"+
                            "Now your remaining balance is "+ totalBalance );
    }

    //method 3
    // account summary
    public void accountSummary(){
        // print the important detail of account
        System.out.println("Account number " + accountNumber);
        System.out.println("Name " + name);
        System.out.println("Total Balance "+totalBalance);
    }






}
