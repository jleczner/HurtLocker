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
            if (grocery != null) {
                groceryList.add(grocery);
            }
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
        outputString += errorCountString();
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

    public String formatGroceryItem(String name) { // width of output blocks is 13 TODO refactor to set width based on longest unique name
        String outputString;
        StringBuilder stringBuilder = new StringBuilder();
        int timesSeen = groceryManager.getNameOccurrences(name);
        List<String> priceList = groceryManager.getUniquePrices(name);

        stringBuilder.append("name:");
        while (stringBuilder.length() + name.length() < 13) { stringBuilder.append(" "); }
        stringBuilder.append(capitalizeFirstLetter(name));
        stringBuilder.append("\t\t");
        stringBuilder.append("seen: ");
        stringBuilder.append(timesSeen);
        stringBuilder.append(" time");
        if (timesSeen > 1) { stringBuilder.append("s"); }

        stringBuilder.append("\n" + "=============" + "\t\t" + "=============" + "\n");

        int i = 1;
        for (String price : priceList) {
            stringBuilder.append("Price:   ");
            stringBuilder.append(price);
            stringBuilder.append("\t\t");
            stringBuilder.append("seen: ");
            int timesPriceSeen = groceryManager.getPriceOccurrences(name, price);
            stringBuilder.append(timesPriceSeen);
            stringBuilder.append(" time");
            if (timesPriceSeen > 1) { stringBuilder.append("s"); }
            if (i++ != priceList.size() || priceList.size() == 1) {
                stringBuilder.append("\n" + "-------------" + "\t\t" + "-------------" + "\n");
            } else {
                stringBuilder.append("\n");
            }
        }
        stringBuilder.append("\n");

        outputString = stringBuilder.toString();
        return outputString;
    }

    public String errorCountString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Errors\t\t\t\t");
        stringBuilder.append("seen: ");
        stringBuilder.append(getErrorCount());
        stringBuilder.append(" time");
        if (getErrorCount() > 1) { stringBuilder.append("s"); }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
