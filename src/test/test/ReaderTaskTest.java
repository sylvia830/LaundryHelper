package test;

import model.LaundryCard;
import model.LaundryTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Reader;
import persistence.ReaderTask;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTaskTest {
    private ReaderTask reader;

    @BeforeEach
        // a call to Reader, for coverage purpose
    void setup() {
        reader = new ReaderTask();
    }

    @Test
    public void testParseTasksFile1() {
        try {
            LinkedList<LaundryTask> tasks  = ReaderTask.readLaundryTask(new File("./data/testTaskFile1.txt"));
            LaundryTask task = tasks.get(0);
            assertEquals(1, task.getMachineID());

            LaundryTask task1 = tasks.get(1);
            assertEquals(2, task1.getMachineID());

            LaundryTask nextTask = new LaundryTask(3);
            assertEquals(3, nextTask.getMachineID());

        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testParseTasksFile2() {
        try {
            LinkedList<LaundryTask> tasks = ReaderTask.readLaundryTask(new File("./data/testTaskFile2.txt"));
            LaundryTask task = tasks.get(0);
            assertEquals(4, task.getMachineID());

            LaundryTask task1 = tasks.get(1);
            assertEquals(5, task1.getMachineID());

            LaundryTask nextTask = new LaundryTask(6);
            assertEquals(6, nextTask.getMachineID());
        } catch (IOException e) {
            fail("IOException should not have been thrown!");
        }
    }

    @Test
    public void testIOException() {
        try {
            ReaderTask.readLaundryTask(new File("./path/does/not/exist/testTasks.txt"));
            fail();
        } catch (IOException e) {
            //expected
        }
    }
}
