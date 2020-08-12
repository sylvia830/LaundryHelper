package model;


// a task queue manager that deals with duplication and print
public class TaskQueueManager extends TaskQueue {
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

    //EFFECTS: print the machineIDs of the tasks in the list
    public int print() {
        //String ids = "";
        for (LaundryTask lt : taskQueue) {
            //ids.concat(String.valueOf(lt.getMachineID()));
            System.out.println(lt.getMachineID());
        }
        return machineID;
    }
}
