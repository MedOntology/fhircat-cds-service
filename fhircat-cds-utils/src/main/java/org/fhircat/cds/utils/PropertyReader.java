package org.fhircat.cds.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public Properties loadProperties() {

        Properties props = new Properties();

        try (InputStream is = PropertyReader.class.getClassLoader().getResourceAsStream("application.properties")) {
            // Load properties from file
            props.load(is);
        } catch (IOException e) {
            System.err.println("Failed to load properties file: " + e.getMessage());
        }

        return props;
    }
}