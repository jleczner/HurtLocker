package leczner.jon.HurtLocker;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class GroceryParserTest {
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
        String proper = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016";
        assertTrue(groceryList.checkValidForm(proper));
    }

    @Test
    public void checkValidFormFailTest() {
        String improper = "naMe:;price:3.23;type:Food;expiration:1/25/2016";
        assertFalse(groceryList.checkValidForm(improper));
    }

    @Test
    public void formatGroceryItemTest() {
        String expected = "name:    Milk \t\t seen: 6 times\n" +
                "============= \t \t =============\n" +
                "Price: \t 3.23\t\t seen: 5 times\n" +
                "-------------\t\t -------------\n" +
                "Price:   1.23\t\t seen: 1 time\n";
        String actual = groceryList.formatGroceryItem("milk");
        assertEquals(expected, actual);
    }

    @Test
    public void getErrorCount() {
//        groceryList.prepData();
        assertEquals(errorCount, groceryList.getErrorCount());
    }

    @Test
    public void finalTest() {
        String expected = "name:    Milk \t\t seen: 6 times\n" +
                "============= \t \t =============\n" +
                "Price: \t 3.23\t\t seen: 5 times\n" +
                "-------------\t\t -------------\n" +
                "Price:   1.23\t\t seen: 1 time\n" +
                "\n" +
                "name:   Bread\t\t seen: 6 times\n" +
                "=============\t\t =============\n" +
                "Price:   1.23\t\t seen: 6 times\n" +
                "-------------\t\t -------------\n" +
                "\n" +
                "name: Cookies     \t seen: 8 times\n" +
                "=============     \t =============\n" +
                "Price:   2.25        seen: 8 times\n" +
                "-------------        -------------\n" +
                "\n" +
                "name:  Apples     \t seen: 4 times\n" +
                "=============     \t =============\n" +
                "Price:   0.25     \t seen: 2 times\n" +
                "-------------     \t -------------\n" +
                "Price:   0.23  \t \t seen: 2 times\n" +
                "\n" +
                "Errors         \t \t seen: 4 times\n";
        String actual = groceryList.formatOutput();
        assertEquals(expected, actual);
    }
}
