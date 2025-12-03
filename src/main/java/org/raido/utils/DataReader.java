package org.raido.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DataReader {

    public static List<Map<String, String>> readJsonData(String fileName) {
        try {
            File file = new File(Objects.requireNonNull(DataReader.class.getClassLoader().getResource(fileName)).getFile());
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(file, new TypeReference<List<Map<String, String>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read test data from JSON file: " + fileName, e);
        }
    }
}