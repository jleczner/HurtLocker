package leczner.jon.HurtLocker;

import java.util.List;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public interface JerkSONParsable {
    String getName();
    List<String> getPrices();
    int getNameOccurrences();
    int getPriceOccurences(String price);
}
