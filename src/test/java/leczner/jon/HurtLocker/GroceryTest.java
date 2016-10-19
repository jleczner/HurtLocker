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
    List<String> milkFields;
    List<String> cheapMilkFields;

    @Before
    public void setup() {
        milkFields = new ArrayList<>();
        milkFields.add("naMe:Milk");
        milkFields.add("pRice:3.23");
        milkFields.add("type:Food");
        milkFields.add("expiration:1/25/2016");

        cheapMilkFields = new ArrayList<>();
        cheapMilkFields.add("naMe:miLK");
        cheapMilkFields.add("pRice:1.23");
        cheapMilkFields.add("type:Food");
        cheapMilkFields.add("expiration:1/25/2016");
    }

    @Test
    public void factoryTest() {
        Grocery milk = Grocery.groceryFactory(milkFields);
        assertTrue(milk.getKey().equals("milk"));
    }

    @Test
    public void factoryDoubleEntryTest() {
        Grocery milk = Grocery.groceryFactory(milkFields);
        Grocery cheapMilk = Grocery.groceryFactory(cheapMilkFields);

    }


}
