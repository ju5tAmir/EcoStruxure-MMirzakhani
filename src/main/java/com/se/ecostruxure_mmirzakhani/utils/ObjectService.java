package com.se.ecostruxure_mmirzakhani.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectService {


    /**
     * Compares two objects.
     *
     * @param firstObject  The first object to compare.
     * @param secondObject The second object to compare.
     * @param <T>          The type of the objects being compared.
     */
    public static <T> void compare(T firstObject, T secondObject) {
        // ToDo : Need recursive implementation for object in object
        //      : Handle exceptions

        // Define pattern for extracting key-value pairs
        String allKeyValuesPattern  = "(\\w+)=((?<==).*?(?=, |}))";

        // Creates matcher for the first object
        Matcher m1 = regex(firstObject.toString(), allKeyValuesPattern);

        // Iterate over key-values of first object
        while (m1.find()) {
            // Retrieve key and value from the first object
            String firstObjectKey       = m1.group(1);
            String firstObjectValue     = m1.group(2);

            // Define pattern to extract the value with the same key from the second object
            // Because in next step we will compare differences
            String specificKeyValue     = String.format("(%s)=((?<==).*?(?=, |}))", firstObjectKey);

            // Create matcher for the second object
            Matcher m2 = regex(secondObject.toString(), specificKeyValue);

            if (m2.find()){
                System.out.println("[+] " + firstObjectKey + ": " + firstObjectValue + " -> " + m2.group(2));
            }
        }
    }

    /**
     * Compiles the given pattern and creates a matcher for the provided text.
     *
     * @param text    The text to match against.
     * @param pattern The regular expression pattern to compile.
     * @return A matcher object for the given text and pattern.
     */
    public static Matcher regex(String text, String pattern) {
        // Compile the given pattern
        Pattern p = Pattern.compile(pattern);

        // Create and return matcher for the text
        return p.matcher(text);
    }

}
