package model;


import java.util.LinkedList;

//a task queue that will be added with laundry task as long as its size is less than MAX_NUM
//NOTE: assume there are only MAX_NUM machines. Every one of these machines support washing and drying as well,
//      but the two functions of the same machine cannot be used at the same time.
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




    //EFFECTS: returns true if there is no task in the queue, otherwise false
    public boolean noTask() {
        if (taskQueue.size() == 0) {
            return true;
        } else {
            return false;
        }

    }

}






