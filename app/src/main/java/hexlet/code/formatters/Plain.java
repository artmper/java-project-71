package hexlet.code.formatters;

import hexlet.code.Utils;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String formate(Map<String, List<Object>> diff) {
        StringBuilder diffResult = new StringBuilder();
        String property = "Property ";

        for (var entry : diff.entrySet()) {
            List<Object> diffInfo = entry.getValue();
            String key = entry.getKey();
            String oldValue = Utils.stringify(diffInfo.get(1));

            if (diffInfo.getFirst().equals("removed")) {
                diffResult.append(property).append("'").append(key).append("'")
                        .append(" was ")
                        .append(diffInfo.getFirst())
                        .append("\n");
            } else  if (diffInfo.getFirst().equals("updated")) {
                String newValue = Utils.stringify(diffInfo.get(2));
                diffResult.append(property).append("'").append(key).append("'")
                        .append(" was ")
                        .append(diffInfo.getFirst())
                        .append(". ")
                        .append("From ")
                        .append(oldValue)
                        .append(" to ")
                        .append(newValue)
                        .append("\n");
            } else  if (diffInfo.getFirst().equals("added")) {
                diffResult.append(property).append("'").append(key).append("'")
                        .append(" was added with value: ")
                        .append(oldValue)
                        .append("\n");
            }
        }

        return diffResult.toString().trim();
    }
}
