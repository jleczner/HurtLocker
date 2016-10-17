package leczner.jon.HurtLocker;


import org.junit.Before;
import org.junit.Test;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class JerkSONParserTest {
    JerkSONParser jerkSONParser;

    @Before
    public void setup() {
        try {
            jerkSONParser = new JerkSONParser(new Main().readRawDataToString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseTokensTest() {

    }

    @Test
    public void formatTokenTest() {

    }

    @Test
    public void formatGroceryItemTest() {

    }

}
