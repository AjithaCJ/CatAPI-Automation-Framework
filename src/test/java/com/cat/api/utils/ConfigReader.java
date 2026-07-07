package com.cat.api.utils;

// These are Java's built-in file handling tools
import java.io.FileInputStream; 
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    // 1. We create a reusable method that accepts a 'key' (like "baseUrl") and returns its value
    public static String getGlobalValue(String key) throws IOException {
        
        // 2. Instantiate Java's Properties class to handle .properties files
        Properties prop = new Properties();
        
        // 3. FileInputStream establishes a data pipeline to read the raw text of your file
        FileInputStream fis = new FileInputStream("src/test/resources/global.properties");
        
        // 4. Load the raw text stream into the Properties object so Java understands the key=value pairs
        prop.load(fis);
        
        // 5. Look up the specific key we asked for and return its value (e.g., the URL string)
        return prop.getProperty(key);
    }
}