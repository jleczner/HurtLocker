package leczner.jon.HurtLocker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class GroceryParser extends JerkSONParser {
    private List<Grocery> groceryList;
    private GroceryManager groceryManager;

    public GroceryParser(String source) {
        super(source);
        groceryList = new ArrayList<>();
    }

    public List<Grocery> getGroceryList() {
        return groceryList;
    }

    @Override
    public void processInput() {
        String[] items = super.parseItems();
        for (String item : items) {
            List<String> fields = processItem(item);
            Grocery grocery = Grocery.groceryFactory(fields);
            groceryList.add(grocery);
        }
        groceryManager = new GroceryManager(groceryList);
    }

    @Override
    public String formatOutput() {
        String outputString = "";
        List<String> uniqueGroceries = groceryManager.getUniqueNames();
        for (String grocery : uniqueGroceries) {
            outputString += formatGroceryItem(grocery);
        }
        return outputString;
    }

    @Override
    public void displayOutput() {
        System.out.println(formatOutput());
    }

    private List<String> processItem(String item) {
        String[] tokens = parseTokens(item);
        return validateTokens(tokens);
    }

    public String formatGroceryItem(String name) {
        String outputString = ""; // TODO
        return outputString;
    }
}
