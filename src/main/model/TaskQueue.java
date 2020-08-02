package model;


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


    //REQUIRES: the queue cannot be empty
    //MODIFIES: this
    //EFFECTS: removes the last added task
    public boolean remove() {
        if (taskQueue.size() == 0) {
            return false;
        } else {
            taskQueue.removeLast();
            return true;
        }
    }

}






