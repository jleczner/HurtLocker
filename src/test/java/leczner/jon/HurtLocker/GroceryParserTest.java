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
    public void processInputTest() {
        groceryList = new GroceryParser("naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food@expiration:1/25/2016##");
        groceryList.processInput();
        assertEquals(1, groceryList.getGroceryList().size());
    }

    @Test
    public void formatGroceryItemTest() {
        groceryList = new GroceryParser("naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food@expiration:1/25/2016##");
        groceryList.processInput();
        String expected = "name:    Milk \t\t seen: 1 time\n" +
                "============= \t \t =============\n" +
                "Price: \t 3.23\t\t seen: 1 times\n";
//        String actual = groceryList.formatGroceryItem(Grocery.groceryFactory()); // TODO FIXME
//        assertEquals(expected, actual);
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
