package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String formate(Map<String, List<Object>> diff) {
        StringBuilder diffResult = new StringBuilder();
        diffResult.append("{\n");

        for (var entry : diff.entrySet()) {
            String key = entry.getKey();
            List<Object> value = entry.getValue();

            if (value.getFirst().equals("removed")) {
                diffResult.append("  ").append("- ").append(key).append(": ")
                        .append(value.get(1).toString())
                        .append("\n");
            } else if (value.getFirst().equals("unchanged")) {
                diffResult.append("    ").append(key).append(": ")
                        .append(value.get(1).toString())
                        .append("\n");
            } else  if (value.getFirst().equals("updated")) {
                diffResult.append("  ").append("- ").append(key).append(": ")
                        .append(value.get(1).toString())
                        .append("\n");
                diffResult.append("  ").append("+ ").append(key).append(": ")
                        .append(value.get(2).toString())
                        .append("\n");
            } else {
                diffResult.append("  ").append("+ ").append(key).append(": ")
                        .append(value.get(1).toString())
                        .append("\n");
            }
        }
        diffResult.append("}");

        return diffResult.toString();
    }
}
