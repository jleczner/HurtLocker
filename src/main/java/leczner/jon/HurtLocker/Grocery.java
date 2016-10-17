package leczner.jon.HurtLocker;

import java.util.List;
import java.util.Map;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class Grocery implements JerkSONParsable {
    private String name;
    private static int nameOccurrences = 0;
    private Map<String, Integer> prices; // key - price string, value - number of occurrences
    private Map<String, List<String>> groceryInfo; // key - name, value - list of prices

    public Grocery(String name, String price) {
        this.name = name;
        nameOccurrences++;
        if (prices.containsKey(price)) {
            int counter = prices.get(price);
            prices.put(price, ++counter);
        } else {
            prices.put(price, 1);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getPrices() {
        return null;
    }

    @Override
    public int getNameOccurrences() {
        return 0;
    }

    @Override
    public int getPriceOccurences(String price) {
        return 0;
    }
}
