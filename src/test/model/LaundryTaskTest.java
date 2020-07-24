package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LaundryTaskTest {

    LaundryTask lt = new LaundryTask(0);
    LaundryTask lt1 = new LaundryTask(1);


    @Test
    public void testGetServiceType(){
        assertEquals(lt.getMachineID(),0);
        assertEquals(lt1.getMachineID(), 1);

    }
}
