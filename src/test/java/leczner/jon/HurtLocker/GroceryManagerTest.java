package leczner.jon.HurtLocker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jonathanleczner on 10/19/16.
 */
public class GroceryManagerTest {
    private GroceryManager groceryManager;
    private List<Grocery> groceryList;

    @Before
    public void setup() {
        groceryList = new ArrayList<>();

        List<String> milkFields = new ArrayList<>();
        milkFields.add("naMe:Milk");
        milkFields.add("pRice:3.23");
        milkFields.add("type:Food");
        milkFields.add("expiration:1/25/2016");
        groceryList.add(Grocery.groceryFactory(milkFields));
        groceryList.add(Grocery.groceryFactory(milkFields)); // dup

        milkFields = new ArrayList<>();
        milkFields.add("naMe:miLk");
        milkFields.add("pRice:3.20");
        milkFields.add("type:Food");
        milkFields.add("expiration:1/25/2016");
        groceryList.add(Grocery.groceryFactory(milkFields));

        List<String> breadFields = new ArrayList<>();
        breadFields.add("naMe:BREAD");
        breadFields.add("pRice:3.20");
        breadFields.add("type:Food");
        breadFields.add("expiration:1/25/2016");
        groceryList.add(Grocery.groceryFactory(breadFields));

        groceryManager = new GroceryManager(groceryList);
    }

    @Test
    public void getNameOccurrencesTest() {
        assertEquals(3, groceryManager.getNameOccurrences("milk"));
    }

    @Test
    public void getPriceOccurrencesTest() {
        assertEquals(2, groceryManager.getPriceOccurrences("milk", "3.23"));
    }

    @Test
    public void getUniqueNamesTest() {
        List<String> expected = new ArrayList<>();
        expected.add("milk");
        expected.add("bread");
        assertEquals(expected, groceryManager.getUniqueNames());
    }

    @Test
    public void getUniquePricesTest() {
        List<String> expected = new ArrayList<>();
        expected.add("3.23");
        expected.add("3.20");
        assertEquals(expected, groceryManager.getUniquePrices("milk"));
    }
}
