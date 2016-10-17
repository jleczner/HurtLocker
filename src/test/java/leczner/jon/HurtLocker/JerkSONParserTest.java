package leczner.jon.HurtLocker;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class JerkSONParserTest {
    private JerkSONParser jerkSONParser;
    private String[] tokens;
    private int errorCount = 4;
    private int groceryCount = 28;

    @Before
    public void setup() {
        try {
            jerkSONParser = new JerkSONParser(new Main().readRawDataToString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        tokens = jerkSONParser.parseTokens();
    }

    @Test
    public void parseTokensTest() {
        assertEquals(groceryCount, tokens.length);
    }

    @Test
    public void formatTokenTest() {
        String naMe = tokens[0];
        String name = jerkSONParser.formatToken(naMe);
        assertTrue("name".equals(name));
    }

    @Test
    public void formatGroceryItemTest() {
        String expected = "name:    Milk \t\t seen: 6 times\n" +
                "============= \t \t =============\n" +
                "Price: \t 3.23\t\t seen: 5 times\n" +
                "-------------\t\t -------------\n" +
                "Price:   1.23\t\t seen: 1 time\n";
        String actual = jerkSONParser.formatGroceryItem("milk");
        assertEquals(expected, actual);
    }

    @Test
    public void checkValidFormPassTest() {
        String proper = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016";
        assertTrue(jerkSONParser.checkValidForm(proper));
    }

    @Test
    public void checkValidFormFailTest() {
        String proper = "naMe:;price:3.23;type:Food;expiration:1/25/2016";
        assertFalse(jerkSONParser.checkValidForm(proper));
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
        String actual = jerkSONParser.formatOutput();
        assertEquals(expected, actual);
    }
}
