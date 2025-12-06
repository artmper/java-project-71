package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static Map<String, Object> getData(String filePath) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = Paths.get(filePath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        String json = Files.readString(path);

        return objectMapper.readValue(json, new TypeReference<Map<String, Object>>(){});
    }
    
    public static String generate(Map<String, Object> file1, Map<String, Object> file2) {
        Map<String, Object> sortedFile1 = Utils.sort(file1);
        Map<String, Object> sortedFile2 = Utils.sort(file2);

        StringBuilder diff = new StringBuilder();
        diff.append("{\n");

        for (var entry : sortedFile1.entrySet()) {
            String firstFileKey = entry.getKey();
            String firstFileValue = entry.getValue().toString();

            if (sortedFile2.containsKey(firstFileKey)) {
                if (sortedFile2.get(firstFileKey).equals(firstFileValue)) {
                    diff.append("    ").append(firstFileKey).append(": ")
                            .append(firstFileValue)
                            .append("\n");
                } else {
                    diff.append("  ").append("- ").append(firstFileKey).append(": ")
                            .append(firstFileValue)
                            .append("\n");
                    diff.append("  ").append("+ ").append(firstFileKey).append(": ")
                            .append(sortedFile2.get(firstFileKey))
                            .append("\n");
                }
                sortedFile2.remove(firstFileKey);
            } else {
                diff.append("  ").append("- ").append(firstFileKey).append(": ")
                        .append(firstFileValue)
                        .append("\n");
            }
        }

        if (!sortedFile2.isEmpty()) {
            for (var entry : sortedFile2.entrySet()) {
                diff.append("  ").append("+ ").append(entry.getKey()).append(": ")
                        .append(entry.getValue())
                        .append("\n");
            }
        }
        diff.append("}");

        return diff.toString();
    }
}
