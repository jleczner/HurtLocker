package leczner.jon.HurtLocker;

import java.util.List;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class GroceryParser extends JerkSONParser {
    private List<Grocery> groceryList;

    public GroceryParser(String source) {
        super(source);
    }

    public Grocery newItem(String groceryString) {
        return null;
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
