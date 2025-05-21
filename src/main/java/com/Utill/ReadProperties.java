package com.Utill;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Utility class for reading values from a properties file.
 */
public class ReadProperties {

    // Shared Properties object used to store loaded property values
    protected static Properties pro = new Properties();

    /**
     * Retrieves the value associated with the given key from the config.properties file.
     *
     * @param key the key for which the value is to be fetched
     * @return the value corresponding to the key, or null if the key does not exist or an error occurs
     */
    public static String getProperties(String key) {

        // Construct the absolute path to the config.properties file
        String filePath = System.getProperty("user.dir").concat("//src//main//resources//config.properties");
        String value = null;

        try (FileInputStream file = new FileInputStream(filePath)) {
            // Load the properties file
            pro.load(file);

            // Get the value for the specified key
            value = pro.getProperty(key);

        } catch (Exception e) {
            e.printStackTrace(); // Print the exception stack trace for debugging
        }

        return value;
    }
}
