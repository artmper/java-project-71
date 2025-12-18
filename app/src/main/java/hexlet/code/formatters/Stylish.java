package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String formate(Map<String, List<Object>> diff) {
        StringBuilder diffResult = new StringBuilder();
        diffResult.append("{\n");

        for (var entry : diff.entrySet()) {
            String key = entry.getKey();
            List<Object> diffInfo = entry.getValue();
            String oldValue = diffInfo.get(1) == null ? "null" : diffInfo.get(1).toString();

            if (diffInfo.getFirst().equals("removed")) {
                diffResult.append("  ").append("- ").append(key).append(": ")
                        .append(oldValue)
                        .append("\n");
            } else if (diffInfo.getFirst().equals("unchanged")) {
                diffResult.append("    ").append(key).append(": ")
                        .append(oldValue)
                        .append("\n");
            } else  if (diffInfo.getFirst().equals("updated")) {
                String newValue = diffInfo.get(2) == null ? "null" : diffInfo.get(2).toString();
                diffResult.append("  ").append("- ").append(key).append(": ")
                        .append(oldValue)
                        .append("\n");
                diffResult.append("  ").append("+ ").append(key).append(": ")
                        .append(newValue)
                        .append("\n");
            } else {
                diffResult.append("  ").append("+ ").append(key).append(": ")
                        .append(oldValue)
                        .append("\n");
            }
        }
        diffResult.append("}");

        return diffResult.toString();
    }
}
