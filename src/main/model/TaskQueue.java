package model;


import javafx.beans.binding.StringExpression;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.LinkedList;

//a task queue that will be added with laundry task as long as its size is less than MAX_NUM
public class TaskQueue {
    public static final int MAX_NUM = 10;
    public int machineID;
    public LaundryCard card;
    LaundryTask lt = new LaundryTask(machineID);
    LinkedList<LaundryTask> taskQueue;
    public String string;

    //EFFECTS: a constructor that creates an empty queue
    public TaskQueue() {
        taskQueue = new LinkedList<LaundryTask>();
    }

    //MODIFIES: this
    //EFFECTS: add a laundryTask to the queue if not full returns true, otherwise false
    public boolean addTask(LaundryTask lt) {
        if (taskQueue.size() >= MAX_NUM) {
            return false;
        } else {
            taskQueue.add(lt);
            return true;
        }
    }


    //EFFECTS: returns true if the size of taskQueue is less than MAX_NUM, otherwise false.
    public boolean isAvailable() {
        if (taskQueue.size() >= MAX_NUM) {
            return false;
        } else {
            return true;
        }
    }


    //EFFECTS: returns true if there is no task in the queue, otherwise false
    public boolean noTask() {
        if (taskQueue.size() == 0) {
            return true;
        } else {
            return false;
        }
    }


    //MODIFIES:this
    //EFFECTS: removes the task that has identical machineID with the rest of the list
    public String noDuplicates(int machineID) {
        for (LaundryTask lt : taskQueue) {
            if (lt.machineID == machineID) {
                taskQueue.remove(taskQueue.indexOf(lt));
                return string = "The machine of your choice is not available right now. "
                        + "Please choose another one or come back later!";

            }
        }
        return string = "Please press 'p' to continue.";
    }


    //EFFECTS: returns the size of the task queue
    public int size() {
        return taskQueue.size();
    }

    //EFFECTS: print the machineIDs of the tasks in the list
    public int print() {
        //String ids = "";
        for (LaundryTask lt : taskQueue) {
            //ids.concat(String.valueOf(lt.getMachineID()));
            System.out.println(lt.getMachineID());
        }
        return machineID;
        //return machineID;
    }
    
}








