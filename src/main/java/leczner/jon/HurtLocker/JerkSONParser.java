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
        return separators.split(item);
    }

    // TODO new function validateTokens(String[])
//            for (String token : tokens) {
//        if (!checkValidForm(token)) {
//            errorCount++;
//            throw new InvalidFormException("Broken token, object unusable");
//        } else {
//        }
//    }

    public boolean checkValidForm(String token) {
        Pattern hasValue = Pattern.compile("[A-Za-z]+:\\w+?");
        Matcher pattern = hasValue.matcher(token);
        return (pattern.find());
    }


    protected String getUnfuzzyName(String name) {
        Pattern upperCaseLetters = Pattern.compile("\\G[A-Z]"); // switch for 0?
        Matcher match = upperCaseLetters.matcher(name);
        StringBuffer sb = new StringBuffer();
        while (match.find()) {
            String upper = match.group();
            int ascii = (int) upper.charAt(0); // should just be one letter to flip
            ascii += 32; // convert case
            String lower = Character.toString((char)ascii);
            match.appendReplacement(sb, lower);
        }
        match.appendTail(sb);

        return name;
    }

    public int getErrorCount() {
        return errorCount;
    }

    // for testing
    public void resetErrorCount() {
        errorCount = 0;
    }
}
