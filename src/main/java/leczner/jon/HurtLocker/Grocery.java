package leczner.jon.HurtLocker;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static leczner.jon.HurtLocker.JerkSONParser.getUnfuzzyName;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class Grocery implements JerkSONParsable {
    private String name;
    private String price;
    private String type;
    private String expiration;

    private Grocery(String name, String price, String type, String expiration) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.expiration = expiration;
    }

    @Override
    public String getKey() {
        return name;
    }

    @Override
    public String getValue() {
        return price;
    }

    public static Grocery groceryFactory(List<String> fields) {
        fields = processFields(fields);
        String name = fields.get(0); // TODO maybe handle exception
        String price = fields.get(1);
        String type = fields.get(2);
        String expiration = fields.get(3);
        return new Grocery(name, price, type, expiration);
    }

    private static List<String> processFields(List<String> fields) {
        List<String> processedFields = new ArrayList<>();
        for (String field : fields) {
            field = processField(field);
            processedFields.add(field);
        }
        return processedFields;
    }

    // separate key and value
    private static String processField(String field) {
        Pattern colonPattern = Pattern.compile(":");
        String[] keyAndValue = colonPattern.split(field);
        String key = keyAndValue[0];
        String value = keyAndValue[1];
        field = processKeyValue(key, value);
        return field;
    }

    // determine field to assign value
    private static String processKeyValue(String key, String value) {
        String unfuzzyKey = getUnfuzzyName(key);
        switch (unfuzzyKey) {
            case "name":
                value = getUnfuzzyName(value);
                break;
            default:
                break;
        }
        return value;
    }
}
