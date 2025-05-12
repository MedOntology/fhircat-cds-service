package org.fhircat.cds.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceFileReader {

    public String getFileContentAsString(String resourcePath) {
        String content = null;

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
             if (inputStream == null) {
                 return null; // Resource not found
             }
             content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
        return content;
    }
}
