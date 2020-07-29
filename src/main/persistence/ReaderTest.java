package persistence;

import model.LaundryCard;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {

    @Test
    public void testParseCardsFile1() {
        try {
            List<LaundryCard> cards = Reader.readLaundryCards(new File("./data/testCardFile1.txt"));
            LaundryCard card = cards.get(0);
            assertEquals(2000, card.getBalance());

            LaundryCard card1 = cards.get(1);
            assertEquals(1000, card1.getBalance());
        } catch (IOException e) {
            fail("IOException should not have been thrown!");
        }
    }

    @Test
    public void testParseCardsFile2() {
        try {
            List<LaundryCard> cards = Reader.readLaundryCards(new File("./data/testCardFile2.txt"));
            LaundryCard card = cards.get(0);
            assertEquals(500, card.getBalance());

            LaundryCard card1 = cards.get(1);
            assertEquals(800, card1.getBalance());
        } catch (IOException e) {
            fail("IOException should not have been thrown!");
        }
    }

    @Test
    public void testIOException() {
        try {
            Reader.readLaundryCards(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            //expected
        }
    }
}
