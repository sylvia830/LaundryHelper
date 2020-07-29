package persistence;

import model.LaundryTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./data/testTasks.txt";
    private Writer testWriter;
    private LaundryTask laundryTask;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        laundryTask = new LaundryTask(7);
    }

    @Test
    void testWriteAccounts() {
        // save laundry task to file
        testWriter.write(laundryTask);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            List<LaundryTask> tasks = Reader.readLaundryTasks(new File(TEST_FILE));
            LaundryTask laundryTask = tasks.get(0);
            assertEquals(7, laundryTask.getMachineID());

            LaundryTask nextTask = new LaundryTask(3);
            assertEquals(3, nextTask.getMachineID());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }


}
