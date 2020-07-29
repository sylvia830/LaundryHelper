package persistence;

import model.LaundryTask;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    @Test
    void testParseAccountsFile1() {
        try {
            List<LaundryTask> tasks = Reader.readLaundryTasks(new File("./data/testTaskFile1.txt"));
            LaundryTask laundryTask = tasks.get(0);
            assertEquals(3, laundryTask.getMachineID());

            LaundryTask lt = tasks.get(1);
            assertEquals(1, lt.getMachineID());

            // check that nextAccountId has been set correctly
            LaundryTask nextTask = new LaundryTask(6);
            assertEquals(6, nextTask.getMachineID());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseAccountsFile2() {
        try {
            List<LaundryTask> tasks = Reader.readLaundryTasks(new File("./data/testTaskFile2.txt"));
            LaundryTask task = tasks.get(0);
            assertEquals(1, task.getMachineID());

            LaundryTask task1 = tasks.get(1);
            assertEquals(2, task1.getMachineID());

            // check that nextAccountId has been set correctly
            LaundryTask nextTask = new LaundryTask(8);
            assertEquals(3, nextTask.getMachineID());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readLaundryTasks(new File("./path/does/not/exist/testTask.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}

