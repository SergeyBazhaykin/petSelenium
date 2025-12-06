package org.raido.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DataReader {

    /**
     * @param fileName Имя файла в папке ресурсов.
     */
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

    /**
     * @param fileName Имя JSON-файла.
     * @param locatorValue Локатор.
     * @return Объект (Map) с соответствующим локатором.
     */
    public static Map<String, String> getObjectByLocator(String fileName, String locatorValue) {
        List<Map<String, String>> allData = readJsonData(fileName);

        List<Map<String, String>> foundObjects = allData.stream()
                .filter(map -> Objects.equals(map.get("locator"), locatorValue))
                .toList();

        if (foundObjects.isEmpty()) {
            throw new RuntimeException(
                    "Error: Object with locator '" + locatorValue + "' not found in " + fileName);
        }

        if (foundObjects.size() > 1) {
            throw new RuntimeException(
                    "Error: Found " + foundObjects.size() + " objects with locator '" + locatorValue +
                            "'. Expected only one.");
        }

        return foundObjects.getFirst();
    }
}