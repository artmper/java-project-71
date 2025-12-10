package hexlet.code.formatters;

import hexlet.code.Utils;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String formate(Map<String, List<Object>> diff) {
        StringBuilder diffResult = new StringBuilder();

        for (var entry : diff.entrySet()) {
            String key = entry.getKey();
            List<Object> diffInfo = entry.getValue();
            String oldValue;
            String newValue = "";

            if (Utils.isPrimitive(diffInfo.get(1))) {
                oldValue = diffInfo.get(1).toString();
            } else if (diffInfo.get(1) instanceof String) {
                if (diffInfo.get(1).equals("null")) {
                    oldValue = "null";
                } else {
                    oldValue = "'" + diffInfo.get(1) + "'";
                }
            } else {
                oldValue = "[complex value]";
            }

            if (diffInfo.size() > 2) {
                if (Utils.isPrimitive(diffInfo.get(2))) {
                    newValue = diffInfo.get(2).toString();
                } else if (diffInfo.get(2) instanceof String) {
                    if (diffInfo.get(2).equals("null")) {
                        newValue = "null";
                    } else {
                        newValue = "'" + diffInfo.get(2) + "'";
                    }
                } else {
                    newValue = "[complex value]";
                }
            }

            if (diffInfo.getFirst().equals("removed")) {
                diffResult.append("Property ").append("'").append(key).append("'")
                        .append(" was ")
                        .append(diffInfo.getFirst())
                        .append("\n");
            } else  if (diffInfo.getFirst().equals("updated")) {
                diffResult.append("Property ").append("'").append(key).append("'")
                        .append(" was ")
                        .append(diffInfo.getFirst())
                        .append(". ")
                        .append("From ")
                        .append(oldValue)
                        .append(" to ")
                        .append(newValue)
                        .append("\n");
            } else  if (diffInfo.getFirst().equals("added")) {
                diffResult.append("Property ").append("'").append(key).append("'")
                        .append(" was added with value: ")
                        .append(oldValue)
                        .append("\n");
            }
        }

        return diffResult.toString().trim();
    }
}
