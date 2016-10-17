package leczner.jon.HurtLocker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public abstract class JerkSONParser {
    private String source;
    private static final String separators = "[!;@^*%]";
    private String[] items;
    private static int errorCount = 0;

    public JerkSONParser(String source) {
        this.source = source;
    }

    public String[] parseItems() { // Pattern.CASE_INSENSITIVE
        items = source.split("##");
        return items;
    }

    public String[] parseTokens(String item) {
        Pattern separators = Pattern.compile(JerkSONParser.separators);
        String[] tokens = separators.split(item);
        for (String token : tokens) {
            if (!checkValidForm(token)) {
                errorCount++;
            }
        }
        return tokens;
    }

    public boolean checkValidForm(String token) {
        Pattern hasValue = Pattern.compile("[A-Za-z]+:\\w+");
        Matcher pattern = hasValue.matcher(token);
        return (pattern.find());
    }

    public int getErrorCount() {
        return errorCount;
    }

    public abstract String formatOutput();

    public abstract void displayOutput();


    // for testing
    public void resetErrorCount() {
        errorCount = 0;
    }
}
