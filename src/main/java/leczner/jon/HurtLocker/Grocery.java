package leczner.jon.HurtLocker;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class Grocery implements JerkSONParsable {
    private String name;
    private String price;

    public Grocery(String name, String price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getKey() {
        return name;
    }

    public String getValue() {
        return price;
    }
}
