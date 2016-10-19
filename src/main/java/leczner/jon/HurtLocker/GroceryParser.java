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
    private Map<String, Map<String, Integer>> groceryInfo; // key=name, value=[key=price, value=numOccurrences] TODO scrap

    public GroceryParser(String source) {
        super(source);
        groceryList = new ArrayList<>();
        names = new HashMap<>();
        groceryInfo = new HashMap<>();
    }

    public List<Grocery> getGroceryList() {
        return groceryList;
    }
    public Map<String, Integer> getNames() {
        return names;
    }
    public Map<String, Map<String, Integer>> getGroceryInfo() {
        return groceryInfo;
    }

    @Override
    public void processInput() {
        String[] items = super.parseItems();
        for (String item : items) {
            List<String> fields = processItem(item);
            Grocery grocery = Grocery.groceryFactory(fields);
            groceryList.add(grocery);
        }
    }

    @Override
    public String formatOutput() {
        return null;
    }

    @Override
    public void displayOutput() {
        System.out.println(formatOutput());
    }

    private List<String> processItem(String item) {
        String[] tokens = parseTokens(item);
        return validateTokens(tokens);
    }

    public Grocery newItem(String name, String price) {
        // later? TODO
//        nameCheck(name);
//        priceCheck(name, price);

//        Grocery item = new Grocery(name, price);
//        groceryList.add(item);
//        return item;
        return null;
    }

    private void nameCheck(String name) {
        if (names.containsKey(name)) {
            int counter = names.get(name);
            names.put(name, ++counter);
        } else {
            names.put(name, 1);
        }
    }
    private void priceCheck(String name, String price) { // TODO refactor for size later
        Map<String, Integer> priceMap;

        if (groceryInfo.containsKey(name)) {
            priceMap = groceryInfo.get(name);
            if (priceMap.containsKey(price)) {
                int counter = priceMap.get(price);
                priceMap.put(price, ++counter);
            } else {
                priceMap.put(price, 1);
            }
        } else {
            priceMap = new HashMap<>();
            priceMap.put(price, 1);
            groceryInfo.put(name, priceMap);
        }
    }

    public int getNameOccurrences(String name) {
        return names.get(name);
    }
    public int getPriceOccurrences(String name, String price) {
        return groceryInfo.get(name).get(price);
    }

    public String formatGroceryItem(String name) {

        return null;
    }
}
