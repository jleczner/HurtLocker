package leczner.jon.HurtLocker;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class GroceryParserTest {
    private GroceryParser groceryList;
    private String[] items;
    private int errorCount = 4;
    private int groceryCount = 28;

    @Before
    public void setup() {
        try {
            groceryList = new GroceryParser(new Main().readRawDataToString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        items = groceryList.parseItems();
        groceryList.resetErrorCount();
    }

    @Test
    public void newItemTest() {
        groceryList.newItem("milk", "5.00");
        assertFalse(groceryList.getGroceryList().isEmpty());
    }

    @Test
    public void getNameOccurrencesTest() {
        groceryList.newItem("milk", "5.00");
        groceryList.newItem("milk", "4.50");
        groceryList.newItem("milk", "4.50");
        groceryList.newItem("bread", "4.50");
        groceryList.newItem("bread", "5.00");
        assertEquals(3, groceryList.getNameOccurrences("milk"));
    }

    @Test
    public void getPriceOccurrencesTest() {
        groceryList.newItem("milk", "5.00");
        groceryList.newItem("milk", "4.50");
        groceryList.newItem("milk", "4.50");
        groceryList.newItem("bread", "4.50");
        groceryList.newItem("bread", "5.00");
        assertEquals(1, groceryList.getPriceOccurrences("bread", "4.50"));
    }

    @Test
    public void processInputTest() {
        groceryList = new GroceryParser("naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food@expiration:1/25/2016##");
        groceryList.processInput();
        assertEquals(1, groceryList.getGroceryList().size());
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
    public void formatOutputTest() {
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
