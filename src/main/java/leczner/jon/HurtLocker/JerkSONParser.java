package leczner.jon.HurtLocker;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class JerkSONParser {
    private String source;
    private static final String[] separators = {";", ":", "@", "^", "*", "%"};
    private static int errorCount = 0;

    public JerkSONParser(String source) {
        this.source = source;
    }

    public String[] parseTokens() { // Pattern.CASE_INSENSITIVE
        return null;
    }

    public String formatGroceryItem() {
        return null;
    }

    public boolean checkValidForm() {
        return false;
    }

    public void formatOutput() {
        return;
    }

    public void displayOutput() {
        return;
    }
}
