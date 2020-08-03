package ui;

import model.LaundryCard;
import model.LaundryTask;
import model.TaskQueue;
import persistence.Reader;
import persistence.ReaderTask;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static model.LaundryCard.AMOUNT;


//laundry helper application
public class LaundryHelper {
    TaskQueue tq = new TaskQueue();
    LinkedList<LaundryTask> taskQueue = new LinkedList<LaundryTask>();
    private Scanner input;
    LaundryCard card = new LaundryCard(0);
    LaundryTask lt = new LaundryTask(1);
    private static final String CARDS_FILE = "./data/cards.txt";
    private static final String TASKS_FILE = "./data/tasks.txt";



    //NOTE: credits to the sample Teller app
    //EFFECTS: runs the laundry helper application
    public LaundryHelper() {
        runLaundryHelper();
    }


    //NOTE: credits to the sample Teller app
    //MODIFIES: this
    //EFFECTS: processes user input
    private void runLaundryHelper() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        loadCards();
        loadTasks();

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
        System.out.println("\tSelect from:");
        System.out.println("\ta -> Add value");
        System.out.println("\tc -> Check balance");
        System.out.println("\tp -> Pay");
        System.out.println("\ts -> Start");
        System.out.println("\tm -> Check the list of unavailable machines(please choose this option after you start)");
        System.out.println("\tv -> Save my current balance");
        System.out.println("\tk -> Save my current machine");
        //System.out.println("\tl -> Load my balance");
        System.out.println("\tq -> Quit");

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
            checkAvailability();
        } else if (command.equals("m")) {
            printMachineID();
        } else if (command.equals("v")) {
            saveCards();
        } else if (command.equals("k")) {
            saveTasks();
        //} else if (command.equals("l")) {
            //loadTasks();
        } else {
            System.out.println("selection not valid");
        }

    }

    //REQUIRES: the machine you choose cannot be the one you have saved to the file
    //MODIFIES: this
    //EFFECTS: check if there are available machines right now and if there is, choose a machine
    private void checkAvailability() {
        if (tq.isAvailable()) {
            System.out.println("You may start now!");
            System.out.println("Choose the machine you want to use: ");
            System.out.println("(washing machine ID: 1-7, dryer ID: 8-10)");
            System.out.println("Note: if there is no further message after your input, "
                    + "then your choice is available. Please press 'p' to proceed.");
            int machineID = input.nextInt();
            lt = new LaundryTask(machineID);
            if (!(taskQueue.size() == 0)) {
                for (LaundryTask lt : taskQueue) {
                    if (lt.machineID == machineID || taskQueue.get(0).getMachineID() == machineID) {
                        System.out.println("The machine of your choice is not available right now. "
                                + "Please choose another one or come back later!");
                        taskQueue.removeLast();
                        tq.remove();
                    }
                }
            }
            tq.addTask(lt);
            taskQueue.add(lt);

        } else {
            System.out.println("The machines are occupied. Please swing by later!");
        }

    }


    //EFFECTS: print out a list of the unavailable machines
    private void printMachineID() {
        if (tq.noTask()) {
            System.out.println("There is no ongoing task. Please press 's' to begin.");
        } else {
            System.out.println("The machines in use right now are  ");
            for (LaundryTask lt : taskQueue) {
                System.out.println(lt.getMachineID());
            }
        }
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
        System.out.println("balance = ¢" + card.getBalance());
    }

    //MODIFIES: this
    //EFFECTS: loads laundryTasks from TASKS_FILE, if that file exists;
    //otherwise initializes laundry tasks with default values
    private void loadCards() {
        try {
            List<LaundryCard> cards = Reader.readLaundryCards(new File(CARDS_FILE));
            card = cards.get(0);
        } catch (IOException e) {
            init();
        }
    }

    private void loadTasks() {
        try {
            taskQueue = ReaderTask.readLaundryTask(new File(TASKS_FILE));
            lt = taskQueue.get(0);
        } catch (IOException e) {
            init();
        }
    }

    //EFFECTS: saves state of machineID and savings accounts to TASKS_FILE
    private void saveCards() {
        try {
            Writer writer = new Writer(new File(CARDS_FILE));
            writer.write(card);
            writer.close();
            System.out.println("Accounts saved to file " + CARDS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + CARDS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //this is due to a programming error
        }
    }

    private void saveTasks() {
        try {
            Writer writer = new Writer(new File(TASKS_FILE));
            writer.write(lt);
            writer.close();
            System.out.println("Accounts saved to file " + TASKS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + TASKS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //this is due to a programming error
        }

    }

    // MODIFIES: this
    // EFFECTS: initializes laundryTask
    private void init() {
        card = new LaundryCard(0);
        lt = new LaundryTask(1);

    }


}
