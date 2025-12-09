package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2) {
        Map<String, Object> firstFileData;
        Map<String, Object> secondFileData;
        try {
            firstFileData = Parser.getData(filepath1);
            secondFileData = Parser.getData(filepath2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> mergedMap = new HashMap<>(firstFileData);
        mergedMap.putAll(secondFileData);
        Map<String, Object> sortedMap = Utils.sort(mergedMap);

        StringBuilder diff = new StringBuilder();
        diff.append("{\n");

        for (var entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue() == null ? "null" : entry.getValue().toString();
            String firstFileValue = firstFileData.get(key) == null ? "null" : firstFileData.get(key).toString();

            if (!secondFileData.containsKey(key)) {
                diff.append("  ").append("- ").append(key).append(": ")
                        .append(value)
                        .append("\n");
            } else if (firstFileData.containsKey(key)) {
                if (firstFileValue.equals(value)) {
                    diff.append("    ").append(key).append(": ")
                            .append(value)
                            .append("\n");
                } else {
                    diff.append("  ").append("- ").append(key).append(": ")
                            .append(firstFileValue)
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
