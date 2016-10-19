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
    public void validateTokensTest() {
        String[] tokens = {"naMe:Milk", "price:3.23", "type:Food", "expiration:1/25/2016"};
        assertFalse(groceryList.validateTokens(tokens).isEmpty());
    }

    @Test
    public void validateTokensErrorTest() {
        String[] tokens = {"naMe:", "price:3.23", "type:Food", "expiration:1/25/2016"};
        assertTrue(groceryList.validateTokens(tokens).isEmpty());
    }

    @Test
    public void checkErrorCountTest() {
        String[] tokens = {"naMe:", "price:3.23", "type:Food", "expiration:1/25/2016"};
        groceryList.validateTokens(tokens);
        assertEquals(1, groceryList.getErrorCount());
    }

    @Test
    public void checkValidFormPassTest() {
        String proper = "naMe:Milk;";
        assertTrue(groceryList.isValidForm(proper));
    }

    @Test
    public void checkValidFormFailTest() {
        String improper = "naMe:;";
        assertFalse(groceryList.isValidForm(improper));
    }

    @Test
    public void fuzzyTester() {
        assertEquals("name", groceryList.getUnfuzzyName("naMe"));
    }

    @Test
    public void fuzzyTesterZero() {
        assertEquals("cookies", groceryList.getUnfuzzyName("c00kieS"));
    }
}
