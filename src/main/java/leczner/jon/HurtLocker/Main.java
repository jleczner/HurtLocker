package leczner.jon.HurtLocker;

import org.apache.commons.io.IOUtils;

public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String source = (new Main()).readRawDataToString();
        JerkSONParser groceryList = new GroceryParser(source);
        groceryList.processInput();
        groceryList.formatOutput();
        groceryList.displayOutput();
    }
}
