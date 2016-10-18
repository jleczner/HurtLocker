package leczner.jon.HurtLocker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class GroceryParser extends JerkSONParser {
    private List<Grocery> groceryList;
    private Map<String, Integer> names;
    private Map<String, Map<String, Integer>> groceryInfo; // key=name, value=[key=price, value=numOccurrences]

    private final int MAX_LEVENSHTEIN = 3;

    public enum GroceryField {
        NAME,
        PRICE,
        TYPE,
        EXPIRATION
    }

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
            processItem(item);
        }
    }

    @Override
    public String formatOutput() {
        return null;
    }

    @Override
    public void displayOutput() {
        return;
    }

    private void processItem(String item) {
        String[] tokens;
        try {
            tokens = parseTokens(item);
        } catch (InvalidFormException e) {
            e.printStackTrace();
            return;
        }
        processTokens(tokens);
    }

    private void processTokens(String[] tokens) {
        for (String token : tokens) {
            processToken(token);
        }
    }

    private void processToken(String token) {
        Pattern colonPattern = Pattern.compile(":");
        String[] keyAndValue = colonPattern.split(token);
        String key = keyAndValue[0];
        String value = keyAndValue[1];
        processKeyValue(key, value);
    }

    private void processKeyValue(String key, String value) {
        if (levenshteinDistance(key, "name") <= MAX_LEVENSHTEIN) {
            String name = value;
            // TODO iterate over names? regex known data errors?
        }
    }


    public Grocery newItem(String name, String price) {
        nameCheck(name);
        priceCheck(name, price);

        Grocery item = new Grocery(name, price);
        groceryList.add(item);
        return item;
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

    // https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Java

    public int levenshteinDistance (CharSequence lhs, CharSequence rhs) {
        int len0 = lhs.length() + 1;
        int len1 = rhs.length() + 1;

        // the array of distances
        int[] cost = new int[len0];
        int[] newcost = new int[len0];

        // initial cost of skipping prefix in String s0
        for (int i = 0; i < len0; i++) cost[i] = i;

        // dynamically computing the array of distances

        // transformation cost for each letter in s1
        for (int j = 1; j < len1; j++) {
            // initial cost of skipping prefix in String s1
            newcost[0] = j;

            // transformation cost for each letter in s0
            for(int i = 1; i < len0; i++) {
                // matching current letters in both strings
                int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;

                // computing cost for each transformation
                int cost_replace = cost[i - 1] + match;
                int cost_insert  = cost[i] + 1;
                int cost_delete  = newcost[i - 1] + 1;

                // keep minimum cost
                newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
            }

            // swap cost/newcost arrays
            int[] swap = cost; cost = newcost; newcost = swap;
        }

        // the distance is the cost for transforming all letters in both strings
        return cost[len0 - 1];
    }
}
