package com.se.ecostruxure_mmirzakhani.utils;

import com.se.ecostruxure_mmirzakhani.be.Change;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectService {


    /**
     * Compares two different objects and returns changes of properties.
     *
     * @param firstObject  The first object to compare.
     * @param secondObject The second object to compare.
     * @param <T>          The type of the objects being compared. both two objects must be from same class.
     */
    public static <T> List<Change> compare(T firstObject, T secondObject) {
        // ToDo : Need recursive implementation for object in object
        //      : Handle exceptions
        List<Change> changes = new ArrayList<>();

        // Check if there is any objects attributes inside the given object
        String objectPattern   = "\\w+=\\w+\\{[^}]*\\}";

        // It matches the entire object with their properties
        Matcher objectsMatches = regex(firstObject.toString(), objectPattern);

        // If there is any objects
        while (objectsMatches.find()){
            String objectClassName = getObjectClassName(objectsMatches.group(0));
            System.out.println("ChildNode: " + objectClassName);

            // Extract the node object from the parent object
            String patternToGetObjectProperties = String.format("%s+\\{[^}]*\\}", objectClassName);

            // Get match from the first parent
            Matcher p1 = regex(firstObject.toString(), patternToGetObjectProperties);

            if (p1.find()){
                System.out.println("First Parent Match: ");
                System.out.println(p1.group());;
            }

            // Get match from the second parent
            Matcher p2 = regex(secondObject.toString(), patternToGetObjectProperties);

            if (p2.find()){
                System.out.println("Second Parent Match: ");
                System.out.println(p2.group());;
            }

        }


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
                // Retrieve key and value from the first object
                String secondObjectKey       = m2.group(1);
                String secondObjectValue     = m2.group(2);
                if (!Objects.equals(firstObjectValue, secondObjectValue)){
//                    System.out.println(firstObjectValue);
//                    System.out.println(secondObjectValue);
//                    System.out.println("Changed: " + firstObjectKey);
                }
            }
        }
        return changes;
    }

    /**
     * Receives an object and returns the object class name
     */
    private static <T> String getObjectClassName(T object){
        // Pattern to get the object class name
        String regexPattern = "(\\w+)\\{";

        Matcher matcher = regex(object.toString(), regexPattern);

        if (matcher.find()){
            return matcher.group(1);
        }
        return null;
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
