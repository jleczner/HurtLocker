package leczner.jon.HurtLocker;

import java.util.List;

/**
 * Created by jonathanleczner on 10/19/16.
 */
public class GroceryManager {
    private List<Grocery> groceryList;

    public GroceryManager(List<Grocery> groceryList) {
        this.groceryList = groceryList;
    }

    public int getNameOccurrences(String name) {
        int occurrences = 0;
        for (Grocery g : groceryList) {
            if (g.getKey().equals(name)) {
                occurrences++;
            }
        }
        return occurrences;
    }

    public int getPriceOccurrences(String name, String price) {
        int occurrences = 0;
        for (Grocery g: groceryList) {
            if (g.getKey().equals(name) && g.getValue().equals(price)) {
                occurrences++;
            }
        }
        return occurrences;
    }
}
