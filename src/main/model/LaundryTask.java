
package model;


// a laundry task with a service type indicating the type of service the user uses,
// the task is added to the task queue if there's available machine.
public class LaundryTask  {
    public int serviceType;


    public LaundryTask(int serviceType) {
        //LaundryTask lt = new LaundryTask(serviceType);
        this.serviceType = serviceType;
    }

    public int getServiceType() {
        return serviceType;
    }

}
