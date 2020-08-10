package test;

import exception.NegativeNumberException;
import model.LaundryCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaundryCardTest {
    LaundryCard card;
    public static int AMOUNT = 125;

    @BeforeEach
    public void setup(){
        card = new LaundryCard(0);
    }

    @Test
    public void testPay() throws NegativeNumberException {
        card.addValue(200);
        card.payFees();
        assertEquals(card.getBalance(), 75);
        card.addValue(100);
        card.payFees();
        assertEquals(card.getBalance(),50);
    }

    @Test
    public void testAddValue(){
        try {
            card.addValue(100);
            card.addValue(10);
        } catch (NegativeNumberException e) {
            fail("not expecting this exception");

        }
        assertEquals(card.getBalance(), 110);
    }


    @Test
    public void testAddNegativeValue(){
        try {
            card.addValue(100);
            card.addValue(-10);
            fail("not expecting to reach this line of code");
        } catch (NegativeNumberException e) {
            //expected
        }
        assertEquals(card.getBalance(),100);

    }

    @Test
    public void testToString(){
        assertEquals(card.toString(), "balance =0");
    }

}