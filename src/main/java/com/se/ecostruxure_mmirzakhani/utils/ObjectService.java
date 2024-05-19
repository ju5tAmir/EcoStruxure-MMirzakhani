package com.se.ecostruxure_mmirzakhani.utils;

import com.se.ecostruxure_mmirzakhani.be.Change;
import com.se.ecostruxure_mmirzakhani.be.ChangeState;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectService {
    //      ToDo: Handle exceptions
    //          : Detect changes, if it was integer and increased and etc
    /**
     * Compares two different objects and returns the changes of their properties.
     *
     * @param firstRootNode  The first object to compare.
     * @param secondRootNode The second object to compare.
     * @param <T>          The type of the objects being compared. both two objects must be from same class.
     */
    public static <T> List<Change> compare(T firstRootNode, T secondRootNode) {
        // List to store all the changes from the terminal nodes
        List<Change> changes = new ArrayList<>();

        // ** First - Check for nodes which are objects
        // *  because if we have a root object as Project to compare with another one
        // *  this project does id, name etc. and meanwhile it does an Employee object with the same attributes
        // *  so in order to compare each of the attributes with the proper one in proper object
        // *  program needs to fetch all the objects first, then starts to compare non-object nodes.
        // *  It goes through all the objects recursively until reaches a terminal node.

        // Pattern to grab all the objects within the root node
        String objectPattern   = "\\w+=\\w+\\{[^}]*\\}";

        // It matches the entire object with their properties
        Matcher objectsMatches = regex(firstRootNode.toString(), objectPattern);

        // If there is any objects
        while (objectsMatches.find()){
            // Class name for the object
            String objectClassName = getObjectClassName(objectsMatches.group(0));

            // Extract the node object from the parent object
            String patternToGetObjectProperties = String.format("%s+\\{[^}]*\\}", objectClassName);

            // Get match from the firstRootNode
            Matcher p1 = regex(firstRootNode.toString(), patternToGetObjectProperties);
            // Get match from the second parent
            Matcher p2 = regex(secondRootNode.toString(), patternToGetObjectProperties);


            if (p1.find() && p2.find()){

                // Compare child nodes recursively
                changes.addAll(compare(p1.group(), p2.group()));
            }


            // If nodes compare was successful, remove the node object from the parent
            firstRootNode = (T) firstRootNode.toString().replace(objectsMatches.group(0), "");
            secondRootNode = (T) secondRootNode.toString().replace(objectsMatches.group(0), "");
        }


        // Define pattern for extracting key-value pairs for the nodes
        String allKeyValuesPattern  = "(\\w+)=((?<==).*?(?=, |}))";

        // Creates matcher for the first object
        Matcher m1 = regex(firstRootNode.toString(), allKeyValuesPattern);

        // Iterate over key-values of first object
        while (m1.find()) {
            // Retrieve key and value from the first object
            String firstObjectKey       = m1.group(1);
            String firstObjectValue     = m1.group(2);

            // Because in next step we will compare differences
            String specificKeyValue     = String.format("(%s)=((?<==).*?(?=, |}))", firstObjectKey);

            // Create matcher for the second object
            Matcher m2 = regex(secondRootNode.toString(), specificKeyValue);


            if (m2.find()){
                // Retrieve key and value from the first object
                String secondObjectKey       = m2.group(1);
                String secondObjectValue     = m2.group(2);
                if (!Objects.equals(firstObjectValue, secondObjectValue)){
                    Change change = new Change();
                    change.setObject(getObjectClassName(firstRootNode));
                    change.setProperty(firstObjectKey);
                    change.setPreviousState(firstObjectValue);
                    change.setCurrentState(secondObjectValue);
                    change.setChangeState(ChangeState.CHANGED);

                    changes.add(change);
                }
            }
        }

        // Reverse the list because it's showing the child objects changes first
        Collections.reverse(changes);

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
