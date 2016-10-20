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
        String expected = "name:    Milk\t\tseen: 1 time\n" +
                "=============\t\t=============\n" +
                "Price:   3.23\t\tseen: 1 time\n" +
                "-------------\t\t-------------\n\n";
        String actual = groceryList.formatGroceryItem("milk");
        assertEquals(expected, actual);
    }

    @Test
    public void formatOutputTest() {
        groceryList = new GroceryParser("naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;priCe:;type:Food;expiration:4/25/2016##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food^expiration:1/04/2016##");
        groceryList.processInput();
        String expected = "name:    Milk\t\tseen: 6 times\n" +
                "=============\t\t=============\n" +
                "Price:   3.23\t\tseen: 5 times\n" +
                "-------------\t\t-------------\n" +
                "Price:   1.23\t\tseen: 1 time\n" +
                "\n" +
                "name:   Bread\t\tseen: 6 times\n" +
                "=============\t\t=============\n" +
                "Price:   1.23\t\tseen: 6 times\n" +
                "-------------\t\t-------------\n" +
                "\n" +
                "name: Cookies\t\tseen: 8 times\n" +
                "=============\t\t=============\n" +
                "Price:   2.25\t\tseen: 8 times\n" +
                "-------------\t\t-------------\n" +
                "\n" +
                "name:  Apples\t\tseen: 4 times\n" +
                "=============\t\t=============\n" +
                "Price:   0.25\t\tseen: 2 times\n" +
                "-------------\t\t-------------\n" +
                "Price:   0.23\t\tseen: 2 times\n" +
                "\n" +
                "Errors\t\t\t\tseen: 4 times\n";
        String actual = groceryList.formatOutput();
        assertEquals(expected, actual);
    }
}
