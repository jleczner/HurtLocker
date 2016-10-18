package leczner.jon.HurtLocker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public abstract class JerkSONParser {
    private String source;
    private static final String separators = "[!;@^*%]";
    private static int errorCount = 0;

    public JerkSONParser(String source) {
        this.source = source;
    }

    public abstract void processInput();
    public abstract String formatOutput();
    public abstract void displayOutput();

    public String[] parseItems() {
        return source.split("##");
    }

    public String[] parseTokens(String item) throws InvalidFormException {
        Pattern separators = Pattern.compile(JerkSONParser.separators);
        String[] tokens = separators.split(item);
        for (String token : tokens) {
            if (!checkValidForm(token)) {
                errorCount++;
            } else {
                throw new InvalidFormException("Broken token, object unusable");
            }
        }
        return tokens;
    }

    public boolean checkValidForm(String token) {
        Pattern hasValue = Pattern.compile("[A-Za-z]+:\\w+?");
        Matcher pattern = hasValue.matcher(token);
        return (pattern.find());
    }

    public int getErrorCount() {
        return errorCount;
    }

    // for testing
    public void resetErrorCount() {
        errorCount = 0;
    }
}
