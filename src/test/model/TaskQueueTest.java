package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TaskQueueTest {
    TaskQueue tq;
    LaundryTask lt = new LaundryTask(1);
    LaundryTask lt1 = new LaundryTask(2);
    LaundryTask lt2 = new LaundryTask(1);
    LaundryTask lt3 = new LaundryTask(1);
    LaundryTask lt4 = new LaundryTask(2);
    LaundryTask lt5 = new LaundryTask(1);
    LaundryTask lt6 = new LaundryTask(2);
    LaundryTask lt7 = new LaundryTask(1);
    LaundryTask lt8 = new LaundryTask(2);
    LaundryTask lt9 = new LaundryTask(1);
    LaundryTask lt10 = new LaundryTask(2);


    @BeforeEach
    public void setup(){

        tq = new TaskQueue();
    }


    @Test
    public void testAddTask(){
        tq.addTask(lt);
        assertTrue(tq.addTask(lt));
        tq.addTask(lt1);
        assertTrue(tq.addTask(lt2));
        tq.addTask(lt2);
        tq.addTask(lt3);
        tq.addTask(lt4);
        tq.addTask(lt5);
        tq.addTask(lt6);
        tq.addTask(lt7);
        tq.addTask(lt8);
        tq.addTask(lt9);
        assertFalse(tq.addTask(lt10));

    }

    @Test
    public void testIsAvailable(){
        assertTrue(tq.isAvailable());
        tq.addTask(lt);
        assertTrue(tq.isAvailable());
        tq.addTask(lt1);
        tq.addTask(lt2);
        tq.addTask(lt3);
        tq.addTask(lt4);
        tq.addTask(lt5);
        tq.addTask(lt6);
        tq.addTask(lt7);
        tq.addTask(lt8);
        tq.addTask(lt9);
        assertFalse(tq.isAvailable());
    }

    @Test
    public void testNoTask(){
        assertTrue(tq.noTask());
        tq.addTask(lt);
        assertFalse(tq.noTask());
        tq.addTask(lt1);
        tq.addTask(lt2);
        assertFalse(tq.noTask());
    }

    @Test
    public void testRemove(){
        assertFalse(tq.remove());
        tq.addTask(lt);
        assertTrue(tq.remove());
        tq.addTask(lt1);
        tq.addTask(lt2);
        assertTrue(tq.remove());
    }

}
