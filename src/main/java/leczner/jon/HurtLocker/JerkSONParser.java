package leczner.jon.HurtLocker;

import java.util.regex.Pattern;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class JerkSONParser {
    private String source;
    private static final String separators = "[!;:@^*%]";
    private String[] tokens;
    private static int errorCount = 0;

    public JerkSONParser(String source) {
        this.source = source;
    }

    public String[] parseItems() { // Pattern.CASE_INSENSITIVE
        tokens = source.split("##");
        return tokens;
    }

    public String formatItem(String item) {
        Pattern separators = Pattern.compile(JerkSONParser.separators);
        String[] elements = separators.split(item);
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

    // https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Java
    public int levenshteinDistance(CharSequence lhs, CharSequence rhs) {
        int len0 = lhs.length() + 1;
        int len1 = rhs.length() + 1;

        // the array of distances
        int[] cost = new int[len0];
        int[] newcost = new int[len0];

        // initial cost of skipping prefix in String s0
        for (int i = 0; i < len0; i++) cost[i] = i;

        // dynamically computing the array of distances

        // transformation cost for each letter in s1
        for (int j = 1; j < len1; j++) {
            // initial cost of skipping prefix in String s1
            newcost[0] = j;

            // transformation cost for each letter in s0
            for(int i = 1; i < len0; i++) {
                // matching current letters in both strings
                int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;

                // computing cost for each transformation
                int cost_replace = cost[i - 1] + match;
                int cost_insert  = cost[i] + 1;
                int cost_delete  = newcost[i - 1] + 1;

                // keep minimum cost
                newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
            }

            // swap cost/newcost arrays
            int[] swap = cost; cost = newcost; newcost = swap;
        }

        // the distance is the cost for transforming all letters in both strings
        return cost[len0 - 1];
    }
}
