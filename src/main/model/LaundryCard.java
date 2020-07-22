package model;

public class LaundryCard {

    //Note that the fees and balance are measured in terms of cent
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

    //MODIFIES: this
    //EFFECTS: pay for the laundry service: less fees against the balance
    public void payFees() {
        balance = balance - AMOUNT;
    }


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


}
