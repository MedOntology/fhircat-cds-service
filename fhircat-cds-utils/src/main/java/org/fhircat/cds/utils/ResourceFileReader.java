package org.fhircat.cds.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceFileReader {

    public String getFileContentAsString(String fileName) {
        String content = null;
        try {
            Path resourcePath = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
            content = Files.readString(resourcePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return content;
    }
}
