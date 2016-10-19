package leczner.jon.HurtLocker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by jonathanleczner on 10/19/16.
 */
public class GroceryTest {
    private List<String> milkFields;

    @Before
    public void setup() {
        milkFields = new ArrayList<>();
        milkFields.add("naMe:Milk");
        milkFields.add("pRice:3.23");
        milkFields.add("type:Food");
        milkFields.add("expiration:1/25/2016");
    }

    @Test
    public void factoryTest() {
        Grocery milk = Grocery.groceryFactory(milkFields);
        assertTrue(milk.getKey().equals("milk"));
    }
}
