package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;

// a laundry card that enables users to add value, pay for services and check balance on account
public class LaundryCard  {

    //Note that the fees and balance are measured in terms of cent
    //Note that washing service fee and drying service fee are the same at AMOUNT
    public int initialBalance;
    public int balance;
    public static int AMOUNT = 125;

    //EFFECTS: a constructor that creates a new laundry card
    public LaundryCard(int initialBalance) {
        // LaundryCard card = new LaundryCard(0);
        this.initialBalance = initialBalance;
        initialBalance = 0;
        balance = initialBalance + balance;
    }

    //Note that the service fee for washing and drying is the same at AMOUNT
    //MODIFIES: this
    //EFFECTS: pay for the laundry service: less fees against the balance
    public void payFees() {
        balance = balance - AMOUNT;
    }


    //REQUIRES: num > 0
    //MODIFIES: this
    //EFFECTS: add value to laundry card
    public void addValue(int num) {
        System.out.println("Please type in the amount you want to deposit:");
        balance = balance + num;
    }

    //EFFECTS: check balance on the laundry card
    public int getBalance() {
        return balance;
    }


//    @Override
//    public String toString() {
//        String machineIDStr = String.format(String.valueOf(balance));
//        return "machineID =" + machineIDStr;
//    }
//
//    @Override
//    public void save(PrintWriter printWriter) {
//        printWriter.print(balance);
//        printWriter.print(Reader.DELIMITER);
//
//    }
}

