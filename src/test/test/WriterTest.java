package test;

import model.LaundryCard;
import model.LaundryTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Reader;
import persistence.ReaderTask;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./data/testCards.txt";
    private static final String TEST1_FILE = "./data/testTasks.txt";
    private Writer testWriter;
    private LaundryCard card;
    private LaundryCard card1;
    private LaundryCard card3;
    private LaundryTask lt;
    private LaundryTask lt1;


    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        card = new LaundryCard(700);
        card1 = new LaundryCard(800);
        card3 = new LaundryCard(8000);
        lt = new LaundryTask(2);
        lt1 = new LaundryTask(3);

    }

    @Test
    void testWriteAccountsCard() {
        // save laundry card to file
        testWriter.write(card);
        testWriter.write(card1);
        testWriter.write(card3);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            List<LaundryCard> cards = Reader.readLaundryCards(new File(TEST_FILE));
            card  = cards.get(0);
            assertEquals(700, card.getBalance());

            card1 = cards.get(1);
            assertEquals(800,card1.getBalance());

            card3 = cards.get(2);
            assertEquals(8000,card3.getBalance());

            LaundryCard nextCard = new LaundryCard(10000);
            assertEquals(10000,nextCard.getBalance());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriteAccountsTask(){
        // save laundry card to file
        testWriter.write(lt);
        testWriter.write(lt1);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            LinkedList<LaundryTask> tasks = ReaderTask.readLaundryTask(new File(TEST_FILE));
            lt  = tasks.get(0);
            assertEquals(2, lt.getMachineID());

            lt1 = tasks.get(1);
            assertEquals(3,lt1.getMachineID());

            LaundryTask nextTask = new LaundryTask(6);
            assertEquals(6,nextTask.getMachineID());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
