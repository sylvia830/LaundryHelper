package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LaundryTaskTest {
    LaundryTask lt = new LaundryTask(3);
    LaundryTask lt1 = new LaundryTask(1);


    @Test
    public void testGetServiceType(){
        assertEquals(lt.getMachineID(),3);
        assertEquals(lt1.getMachineID(), 1);

    }

    @Test
    public void testToString(){
        assertTrue(lt.toString().contains("machineID =3"));
    }


}
