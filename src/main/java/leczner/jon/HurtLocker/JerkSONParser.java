package leczner.jon.HurtLocker;

import java.util.ArrayList;
import java.util.List;
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

    public String[] parseTokens(String item) {
        Pattern separators = Pattern.compile(JerkSONParser.separators);
        return separators.split(item);
    }

    public List<String> validateTokens(String[] tokens) {
        List<String> validTokens = new ArrayList<>();
        for (String token : tokens) {
            try {
                validateToken(token);
            } catch (InvalidFormException e) {
                errorCount++;
                break;
            }
            validTokens.add(token);
        }
        return validTokens;
    }

    public void validateToken(String token) throws InvalidFormException {
        if (!isValidForm(token)) {
            throw new InvalidFormException("Broken token, object unusable");
        }
    }

    public boolean isValidForm(String token) {
        Pattern hasValue = Pattern.compile("[A-Za-z]+:\\w+?");
        Matcher pattern = hasValue.matcher(token);
        return (pattern.find());
    }

    public static String getUnfuzzyName(String name) {
        Pattern upperCaseLetters = Pattern.compile("([A-Z]|0)");
        Matcher match = upperCaseLetters.matcher(name);
        StringBuffer sb = new StringBuffer();
        while (match.find()) {
            String unfuzzyMatch = getUnfuzzyMatch(match.group());
            match.appendReplacement(sb, unfuzzyMatch);
        }
        match.appendTail(sb);
        name = sb.toString();
        return name;
    }

    private static String getUnfuzzyMatch(String match) {
        String unfuzzyMatch;
        switch (match) {
            case "0":
                unfuzzyMatch = Character.toString((char)111); // ascii for 'o'
                break;
            default:
                int ascii = (int) match.charAt(0); // should just be one letter to flip
                ascii += 32; // convert case
                unfuzzyMatch = Character.toString((char)ascii);
        }
        return unfuzzyMatch;
    }

    public static String capitalizeFirstLetter(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        char upper = Character.toUpperCase((char)name.charAt(0));
        String upperString = Character.toString(upper);
        sb.replace(0, 1, upperString);
        return sb.toString();
    }

    public int getErrorCount() {
        return errorCount;
    }

    // for testing
    public void resetErrorCount() {
        errorCount = 0;
    }
}
