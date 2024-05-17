package com.se.ecostruxure_mmirzakhani.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FlagService {

    private static final String FLAGS_FOLDER_PATH = "src/main/resources/flags";
    private static final Map<String, Image> flagMap = new HashMap<>();

    static {
        // Load flag images into the map
        loadFlags();
    }

    private static void loadFlags() {
        File flagsFolder = new File(FLAGS_FOLDER_PATH);
        if (flagsFolder.isDirectory()) {
            File[] flagFiles = flagsFolder.listFiles((dir, name) -> name.endsWith(".png"));
            if (flagFiles != null) {
                for (File flagFile : flagFiles) {
                    String fileName = flagFile.getName();
                    // Extract country code from the file name
                    String countryCode = extractCountryCode(fileName);
                    Image flagImage = new Image(flagFile.toURI().toString());
                    flagMap.put(countryCode, flagImage);
                }
            }
        }
    }

    private static String extractCountryCode(String fileName) {
        // Extract country code from the file name
        String[] parts = fileName.split("\\.");
        String countryCode = parts[0].toUpperCase();
        if (countryCode.length() == 2 && !countryCode.equals("GB")) {
            return countryCode; // Two-letter code
        } else if (countryCode.startsWith("GB-")) {
            return countryCode.substring(3); // Extracting from "GB-XXX" format
        } else {
            return null; // Invalid or unsupported code format
        }
    }

    public static ImageView getFlagImageView(String countryCode) {
        Image flagImage = flagMap.get(countryCode);
        if (flagImage != null) {
            ImageView imageView = new ImageView(flagImage);
            imageView.setFitWidth(32); // Adjust width and height as needed
            imageView.setFitHeight(32);
            return imageView;
        } else {
            // Return a default image or handle missing flag
            return null;
        }
    }
}

