package test;

import model.LaundryTask;
import model.TaskQueueManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskQueueManagerTest {
    TaskQueueManager tqm;
    LaundryTask lt = new LaundryTask(1);
    LaundryTask lt1 = new LaundryTask(2);

    @BeforeEach
    public void setup() {
        tqm = new TaskQueueManager();
    }

    @Test
    public void noDuplicates() {
        tqm.addTask(lt);
        tqm.addTask(lt1);
        tqm.noDuplicates(0);
        assertEquals(2, tqm.size());
        tqm.noDuplicates(1);
        assertEquals(1, tqm.size());
    }

    @Test
    public void testPrint() {
        tqm.addTask(lt);
        assertEquals(0, tqm.print());
    }

}
