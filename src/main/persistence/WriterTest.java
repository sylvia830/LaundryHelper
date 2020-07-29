package persistence;

import model.LaundryCard;
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
    private static final String TEST_FILE = "./data/testCards.txt";
    private Writer testWriter;
    private LaundryCard card;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        card = new LaundryCard(700);
    }

    @Test
    void testWriteAccounts() {
        // save laundry card to file
        testWriter.write(card);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            List<LaundryCard> cards = Reader.readLaundryCards(new File(TEST_FILE));
            LaundryCard card  = cards.get(0);
            assertEquals(700, card.getBalance());


        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }


}
