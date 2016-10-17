package leczner.jon.HurtLocker;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class JerkSONParser {
    private String source;
    private static final String[] separators = {"!", ";", ":", "@", "^", "*", "%"};
    private static int errorCount = 0;

    public JerkSONParser(String source) {
        this.source = source;
    }

    public String[] parseTokens() { // Pattern.CASE_INSENSITIVE
        String[] tokens = source.split("##");
        return tokens;
    }

    public String formatToken(String token) {
        return null;
    }

    public String formatGroceryItem(String item) {
        return null;
    }

    public boolean checkValidForm(String item) {
        return false;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public String formatOutput() {
        return null;
    }

    public void displayOutput() {
        return;
    }
}
