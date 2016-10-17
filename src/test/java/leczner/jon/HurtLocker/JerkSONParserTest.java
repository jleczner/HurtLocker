package leczner.jon.HurtLocker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class JerkSONParserTest {
    private GroceryParser groceryList;
    private String[] tokens;
    private int errorCount = 4;
    private int groceryCount = 28;

    @Before
    public void setup() {
        try {
            groceryList = new GroceryParser(new Main().readRawDataToString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        tokens = groceryList.parseItems();
        groceryList.resetErrorCount();
    }

    @Test
    public void parseItemsTest() {
        assertEquals(groceryCount, tokens.length);
    }

    @Test
    public void parseTokensTest() {
        String input = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016";
        String[] expected = {"naMe:Milk", "price:3.23", "type:Food", "expiration:1/25/2016"};
        String[] actual = groceryList.parseTokens(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void parseTokensErrorTest() {
        String input = "naMe:;price:3.23;type:Food;expiration:1/25/2016";
        groceryList.parseTokens(input);
        assertEquals(1, groceryList.getErrorCount());
    }

    @Test
    public void checkValidFormPassTest() {
        String proper = "naMe:Milk;";
        assertTrue(groceryList.checkValidForm(proper));
    }

    @Test
    public void checkValidFormFailTest() {
        String improper = "naMe:;";
        assertFalse(groceryList.checkValidForm(improper));
    }
}
