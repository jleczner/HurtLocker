package leczner.jon.HurtLocker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class GroceryParser extends JerkSONParser {
    private List<Grocery> groceryList;
    private Map<String, Integer> names;
    private Map<String, Integer> prices;

    public GroceryParser(String source) {
        super(source);
        groceryList = new ArrayList<>();
        names = new HashMap<>();
        prices = new HashMap<>();
    }

    public Grocery newItem(String name, String price) {
        nameCheck(name);
        priceCheck(price);

        Grocery item = new Grocery(name, price);
        groceryList.add(item);
        return item;
    }

    public void nameCheck(String name) {
        if (names.containsKey(name)) {
            int counter = names.get(name);
            names.put(name, ++counter);
        } else {
            names.put(name, 1);
        }
    }

    public void priceCheck(String price) {
        if (prices.containsKey(price)) {
            int counter = prices.get(price);
            prices.put(price, ++counter);
        } else {
            prices.put(price, 1);
        }
    }

    public String formatGroceryItem(String name) {
        return null;
    }

    @Override
    public String formatOutput() {
        return null;
    }

    @Override
    public void displayOutput() {

    }
}
