package hexlet.code.formatters;

import hexlet.code.Status;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String formate(Map<String, Status> diff) throws IllegalStateException {
        StringBuilder diffResult = new StringBuilder();
        String property = "Property ";

        for (var entry : diff.entrySet()) {
            Status diffInfo = entry.getValue();
            String key = stringify(entry.getKey());

            String statusName = diffInfo.getStatusName();
            String oldValue = stringify(diffInfo.getOldValue());
            String newValue = stringify(diffInfo.getNewValue());

            switch (statusName) {
                case Status.DELETED -> diffResult.append(property).append(key)
                        .append(" was ")
                        .append("removed")
                        .append("\n");
                case Status.UPDATED ->
                    diffResult.append(property).append(key)
                            .append(" was ")
                            .append("updated")
                            .append(". ")
                            .append("From ")
                            .append(oldValue)
                            .append(" to ")
                            .append(newValue)
                            .append("\n");
                case Status.ADDED -> diffResult.append(property).append(key)
                        .append(" was added with value: ")
                        .append(newValue)
                        .append("\n");
                case Status.UNCHANGED -> {
                    //При формате вывода "plain" мы не выводим информацию о паре ключ-значение,
                    //которая осталась нетронутой во втором файле
                }
                default -> throw new IllegalStateException("Unknown status name: " + statusName);
            }
        }

        return diffResult.toString().trim();
    }

    public static String stringify(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof List || value instanceof java.util.Map) {
            return "[complex value]";
        } else {
            return value.toString();
        }
    }
}
