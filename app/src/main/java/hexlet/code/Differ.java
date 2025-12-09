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
