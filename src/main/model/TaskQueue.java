package model;


import java.util.LinkedList;

//a task queue that will be added with laundry task as long as its size is less than MAX_NUM
public class TaskQueue {
    public static final int MAX_NUM = 10;
    public int serviceType;
    public int test;
    LaundryTask lt = new LaundryTask(serviceType);
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


    //EFFECTS: prints out the service type of all the laundry tasks in taskQueue
    public void printServiceType() {
        if (taskQueue.size() == 0) {
            test = -1;
            System.out.println("There is no ongoing task. Please press a to begin.");
        } else {
            System.out.println("The types of services in use right now are  ");
            test = 1;
            for (LaundryTask lt : taskQueue) {
                lt.getServiceType();
                System.out.println(lt.getServiceType());
            }
        }
    }

    public int getTest() {
        return test;
    }


}



