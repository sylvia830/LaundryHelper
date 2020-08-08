package ui;

import model.LaundryCard;
import model.LaundryTask;
import model.TaskQueue;
import persistence.Reader;
import persistence.ReaderTask;
import persistence.Writer;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static model.LaundryCard.AMOUNT;


//laundry helper application
public class LaundryHelper extends JFrame implements ActionListener, ListSelectionListener {
    TaskQueue tq = new TaskQueue();
    LinkedList<LaundryTask> taskQueue = new LinkedList<LaundryTask>();
    private Scanner input;
    LaundryCard card = new LaundryCard(0);
    LaundryTask lt = new LaundryTask(1);
    private static final String CARDS_FILE = "./data/cards.txt";
    private static final String TASKS_FILE = "./data/tasks.txt";
    DefaultListModel<Integer> listModel = new DefaultListModel<>();
    JList<Integer> tasks = new JList<>();
    JFrame frame = new JFrame("Laundry Helper");
    JScrollPane listScrollPane = new JScrollPane(tasks);
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JLabel label1 = new JLabel("Manage your laundry card balance here!");
    JButton addValue = new JButton("Add value");
    JTextField addValueBox = new JTextField(8);
    JButton pay = new JButton("Pay");
    JButton checkBalance = new JButton("Check balance");
    JLabel label2 = new JLabel("Create a new laundry task here!(Note: if you wish to delete the current machine, "
            + "do it before saving)");
    JButton deleteMyCurrentTask = new JButton("Delete my current task");
    JButton start = new JButton("Start");
    JTextField chooseMyMachine = new JTextField(10);
    JLabel listOfMachines = new JLabel("The machines in use are:");
    JButton saveMyCurrentMachine = new JButton("Save my current machine");
    JButton saveMyCurrentBalance = new JButton("Save my current balance");
    Icon icon = new ImageIcon("data/washingMachine.png");
    JLabel washingMachine = new JLabel(icon);
    Icon icon1 = new ImageIcon("data/laundry card.png");
    JLabel laundryCardIcon = new JLabel(icon1);
    Container contentPane = frame.getContentPane();

    //NOTE: credits to the sample Teller app
    //EFFECTS: runs the laundry helper application
    public LaundryHelper() {
        super("Laundry Helper");
        //create a list that displays the list of unavailable machines
        tasks.setModel(listModel);
        //tasks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //tasks.setSelectedIndex(0);
        tasks.addListSelectionListener(this);
        tasks.setVisibleRowCount(10);
        //create a new window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set window size
        frame.setSize(2000, 500);
        //display window
        frame.setVisible(true);
        //content pane
        contentPane.setLayout(new BorderLayout());
        label1.setFont(new Font("Arial", Font.BOLD, 20));
        label1.setForeground(Color.BLUE);
        label2.setFont(new Font("Arial", Font.BOLD, 20));
        label2.setForeground(Color.BLUE);

        addElement();

        initialiseAddValue();
        initialiseAddValueBox();
        initialisePay();
        initialiseSaveMyCurrentBalance();
        initialiseCheckBalance();
        initialiseStart();
        initialiseChooseMyMachine();
        initialiseSaveMyCurrentMachine();
        initialiseDeleteMyCurrentMachine();

        runLaundryHelper();
    }

    //MODIFIES: this
    //EFFECTS: add new control elements to content pane
    public void addElement() {
        panel.add(laundryCardIcon);
        panel.add(label1);
        panel.add(addValue);
        panel.add(addValueBox);
        panel.add(pay);
        panel.add(saveMyCurrentBalance);
        panel.add(checkBalance);
        panel1.add(washingMachine);
        panel1.add(Box.createHorizontalStrut(10));
        panel1.add(new JSeparator(SwingConstants.HORIZONTAL));
        panel1.add(start);
        panel1.add(chooseMyMachine);
        panel1.add(listOfMachines);
        panel1.add(tasks);
        //panel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel1.add(saveMyCurrentMachine);
        panel1.add(deleteMyCurrentTask);
        panel2.add(label2);
        //contentPane.add(listScrollPane, BorderLayout.CENTER);
        contentPane.add(panel1, BorderLayout.PAGE_START);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(panel, BorderLayout.PAGE_END);
        contentPane.add(panel2,BorderLayout.PAGE_START);
        contentPane.add(panel1,BorderLayout.CENTER);
    }

    //EFFECTS: initialise add value action listener
    public void initialiseAddValue() {
        addValue.addActionListener((e) -> {
            System.out.println("Enter an amount to deposit (in cents):");
        });
    }

    //EFFECTS: initialise add value box action listener
    public void initialiseAddValueBox() {
        addValueBox.addActionListener((e) -> {
            setAddValueBox();
        });
    }

    //EFFECTS: initialise pay action listener
    public void initialisePay() {
        pay.addActionListener((e) -> {
            doPay();
        });
    }

    //EFFECTS: initialise save my current balance action listener
    public void initialiseSaveMyCurrentBalance() {
        saveMyCurrentBalance.addActionListener((e) -> {
            saveCards();
            saveBalancePane();
        });
    }

    //EFFECTS: initialise check balance action listener
    public void initialiseCheckBalance() {
        checkBalance.addActionListener((e) -> {
            printBalance(card);
        });
    }

    //EFFECTS: initialise start action listener
    public void initialiseStart() {
        start.addActionListener((e) -> {
            start();
        });
    }

    //EFFECTS: initialise choose my machine action listener
    public void initialiseChooseMyMachine() {
        chooseMyMachine.addActionListener((e) -> {
            chooseMyMachine();
        });
    }

    //EFFECTS: initialise save my current machine action listener
    public void initialiseSaveMyCurrentMachine() {
        saveMyCurrentMachine.addActionListener((e) -> {
            saveTasks();
            saveTaskPane();
        });
    }

    //EFFECTS: initialise delete my current machine action listener
    public void initialiseDeleteMyCurrentMachine() {
        deleteMyCurrentTask.addActionListener((e) -> {
            deleteMyMachine();
        });
    }

    //MODIFIES: this
    //EFFECTS: add value to current balance
    public void setAddValueBox() {
        String deposit = addValueBox.getText();
        card.addValue(Integer.valueOf(deposit));
        printBalance(card);
    }

    //MODIFIES: this
    //EFFECTS: check machine availability and choose a machine if available
    public void chooseMyMachine() {
        if (tq.isAvailable()) {
            int machineID = Integer.valueOf(chooseMyMachine.getText());
            lt = new LaundryTask(machineID);
            System.out.println(tq.noDuplicates(machineID));
            tq.addTask(lt);
            if (!listModel.contains(Integer.valueOf(chooseMyMachine.getText()))) {
                listModel.addElement(Integer.parseInt(chooseMyMachine.getText()));
            }
        } else {
            System.out.println("The machines are occupied. Please swing by later!");
        }
    }

    //REQUIRES: cannot delete after saving the machine to files
    //MODIFIES: this
    //EFFECTS: delete the current machine
    public void deleteMyMachine() {
        listModel.removeElementAt(listModel.size() - 1);
        tq.remove();
    }

    //EFFECTS: start to play sound
    //Note: the startSound .wav file was downloaded from SoundBible.com
    public void start() {
        System.out.println("You may start now!");
        System.out.println("Choose the machine you want to use: ");
        System.out.println("(washing machine ID: 1-7, dryer ID: 8-10)");
        System.out.println("Note: if there is no further message after your input, "
                + "then your choice is available. Please press 'p' to proceed.");
        File startSound = new File("./data/start.wav");
        playSound(startSound);
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
            System.out.println(tq.noDuplicates(machineID));
            tq.addTask(lt);
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
            tq.print();
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
    //EFFECTS: loads laundryCards from CARDS_FILE, if that file exists;
    //otherwise initializes laundry tasks with default values
    private void loadCards() {
        try {
            List<LaundryCard> cards = Reader.readLaundryCards(new File(CARDS_FILE));
            card = cards.get(0);
        } catch (IOException e) {
            init();
        }
    }

    //MODIFIES: this
    //EFFECTS: loads laundryTasks from TASKS_FILE, if that file exists;
    //otherwise initializes laundry tasks with default values
    private void loadTasks() {
        try {
            taskQueue = ReaderTask.readLaundryTask(new File(TASKS_FILE));
            lt = taskQueue.get(0);
            tq.addTask(lt);
            listModel.addElement(lt.getMachineID());
        } catch (IOException e) {
            init();
        }
    }

    //EFFECTS: saves state of balance to CARDS_FILE
    private void saveCards() {
        try {
            Writer writer = new Writer(new File(CARDS_FILE));
            writer.write(card);
            writer.close();
            System.out.println("Balance saved to file " + CARDS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + CARDS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //this is due to a programming error
        }
    }

    //EFFECTS: save the state of task's machineID to TASKS_FILE
    private void saveTasks() {
        try {
            Writer writer = new Writer(new File(TASKS_FILE));
            writer.write(lt);
            writer.close();
            System.out.println("Task saved to file " + TASKS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + TASKS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //this is due to a programming error
        }

    }

    //EFFECTS: enable the program to play wave files
    public void playSound(File sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();

        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes laundryTask
    private void init() {
        card = new LaundryCard(0);
        lt = new LaundryTask(1);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    //EFFECTS: create a JOptionPane
    private void saveTaskPane() {
        JOptionPane.showMessageDialog(this, "Your current machine is saved!");
        System.out.println("saveTaskJOptionPane exit!");
    }

    //EFFECTS: create a JOptionPane
    private void saveBalancePane() {
        JOptionPane.showMessageDialog(this, "Your current balance is saved!");
        System.out.println("saveBalanceJOptionPane exit!");
    }


}





