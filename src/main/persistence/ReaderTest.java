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

            LaundryCard card2 = cards.get(2);
            assertEquals(200,card2.getBalance());

            LaundryCard card3 = cards.get(3);
            assertEquals(100, card3.getBalance());

            LaundryCard card4 = cards.get(4);
            assertEquals(500,card4.getBalance());

            LaundryCard nextCard = new LaundryCard(800);
            assertEquals(800, nextCard.getBalance());


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

            LaundryCard nextCard = new LaundryCard(700);
            assertEquals(700,nextCard.getBalance());
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
