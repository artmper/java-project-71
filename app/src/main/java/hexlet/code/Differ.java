package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Differ {

    public static Map<String, Object> getData(String filePath) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = Paths.get(filePath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        String json = Files.readString(path);

        return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() { });
    }

    public static String generate(Map<String, Object> file1, Map<String, Object> file2) {
        Map<String, Object> mergedMap = new HashMap<>(file1);
        mergedMap.putAll(file2);
        Map<String, Object> sortedMap = Utils.sort(mergedMap);

        StringBuilder diff = new StringBuilder();
        diff.append("{\n");

        for (var entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();

            if (!file2.containsKey(key)) {
                diff.append("  ").append("- ").append(key).append(": ")
                        .append(value)
                        .append("\n");
            } else if (file1.containsKey(key)) {
                if (file1.get(key).equals(value)) {
                    diff.append("    ").append(key).append(": ")
                            .append(value)
                            .append("\n");
                } else {
                    diff.append("  ").append("- ").append(key).append(": ")
                            .append(file1.get(key))
                            .append("\n");
                    diff.append("  ").append("+ ").append(key).append(": ")
                            .append(value)
                            .append("\n");
                }
            } else {
                diff.append("  ").append("+ ").append(key).append(": ")
                        .append(value)
                        .append("\n");
            }
        }
        diff.append("}");

        return diff.toString();
    }
}
