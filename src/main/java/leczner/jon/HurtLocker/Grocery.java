package leczner.jon.HurtLocker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class Grocery implements JerkSONParsable {
    private String name;
    private static List<String> prices = new ArrayList<>();

    public Grocery(String name, String price) {
        this.name = name;
        prices.add(price);
    }

    @Override
    public String getKey() {
        return name;
    }

    public List<String> getValue() {
        return prices;
    }

    // TODO add support for other fields
}
