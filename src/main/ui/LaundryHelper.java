package ui;

import model.LaundryCard;
import model.LaundryTask;
import model.TaskQueue;
import java.util.Scanner;

import static model.LaundryCard.AMOUNT;
import static model.TaskQueue.MAX_NUM;

//laundry helper application
public class LaundryHelper {
    TaskQueue tq = new TaskQueue();
    private Scanner input;
    LaundryCard card = new LaundryCard(0);
    LaundryTask lt;


    //NOTE: credits to the sample Teller app
    //EFFECTS: runs the laundry helper application
    public LaundryHelper() {
        runLaundryHelper();
    }


    ////NOTE: credits to the sample Teller app
    //MODIFIES: this
    //EFFECTS: processes user input
    private void runLaundryHelper() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);


        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThank you! Goodbye!");
    }



    //NOTE: credits to the sample Teller app
    //EFFECTS: display menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\na -> Add value");
        System.out.println("\nc -> Check balance");
        System.out.println("\np -> Pay");
        System.out.println("\ns -> Start");
        System.out.println("\nt -> Check the list of ongoing task (1 for washing, 2 for drying)");

    }


    //NOTE: credits to the sample Teller app
    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddValue();
        } else if (command.equals("c")) {
            printBalance(card);
        } else if (command.equals("p")) {
            doPay();
        } else if (command.equals("s")) {
            //tq.isAvailable();
            checkAvailability();
        } else if (command.equals("t")) {
            printServiceType();
        } else {
            System.out.println("selection not valid");
        }

    }

    //MODIFIES: this
    //EFFECTS: check if there are available machines right now and if there is enter the type of service
    private void checkAvailability() {
        if (tq.isAvailable()) {
            System.out.println("You may start now!");
            System.out.println("Choose the type of service you want to use (1 for washing machine, 2 for dryer): ");
            int serviceType = input.nextInt();
            lt = new LaundryTask(serviceType);
            tq.addTask(lt);
            System.out.println("Please press p to proceed.");

        } else {
            System.out.println("The machines are occupied. Please swing by later!");
        }
    }


    //EFFECTS: print out a list of the service used by the customers
    private void printServiceType() {

        tq.printServiceType();

    }



    //MODIFIES: this
    //EFFECTS: pays for the service
    private void doPay() {
        System.out.println("Your total cost is 125 cents.");

        if (card.getBalance() < AMOUNT) {
            System.out.println("Insufficient balance on account. Please add value before paying!");
        } else {
            card.payFees();
            System.out.println("Your payment has been processed!");
            System.out.println("Your payment is successful! Thanks for supporting our service.");
        }

    }


    //NOTE: credits to the sample Teller app
    //MODIFIES: this
    //EFFECTS: add value to the current balance
    private void doAddValue() {
        //LaundryCard card = new LaundryCard(0);
        System.out.println("Enter an amount to deposit (in cents):");
        int num = input.nextInt();

        if (num >= 0) {
            card.addValue(num);
        } else {
            System.out.println("Cannot add negative amount...\n");
        }
        printBalance(card);
    }


    //EFFECTS: print current balance of the laundry card
    private void printBalance(LaundryCard card) {
        System.out.println("balance = " + card.getBalance());
    }


}
