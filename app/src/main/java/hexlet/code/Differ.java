package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class Differ {

    public static String generate(Map<String, Object> file1, Map<String, Object> file2) {
        Map<String, Object> mergedMap = new HashMap<>(file1);
        mergedMap.putAll(file2);
        Map<String, Object> sortedMap = Utils.sort(mergedMap);

        StringBuilder diff = new StringBuilder();
        diff.append("{\n");

        for (var entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue() == null ? "null" : entry.getValue().toString();
            String firstFileValue = file1.get(key) == null ? "null" : file1.get(key).toString();

            if (!file2.containsKey(key)) {
                diff.append("  ").append("- ").append(key).append(": ")
                        .append(value)
                        .append("\n");
            } else if (file1.containsKey(key)) {
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
