
package model;


// a laundry task with a machineID indicating the exact machine the user uses,
// the task is added to the task queue if there's available machine.
public class LaundryTask  {
    public int machineID;


    //a constructor that creates a new laundry task
    public LaundryTask(int machineID) {
        this.machineID = machineID;
    }


    //EFFECTS: return the machineID of the given laundry task
    public int getMachineID() {
        return machineID;
    }

}
