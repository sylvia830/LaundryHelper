package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LaundryTaskTest {

    LaundryTask lt = new LaundryTask(0);
    LaundryTask lt1 = new LaundryTask(1);


    @Test
    public void testGetServiceType(){
        assertEquals(lt.getServiceType(),0);
        assertEquals(lt1.getServiceType(), 1);

    }
}
