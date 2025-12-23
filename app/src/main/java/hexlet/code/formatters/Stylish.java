package hexlet.code.formatters;

import hexlet.code.Status;

import java.util.Map;

public class Stylish {
    public static String formate(Map<String, Status> diff) throws IllegalStateException {
        StringBuilder diffResult = new StringBuilder();
        diffResult.append("{\n");

        for (var entry : diff.entrySet()) {
            Status diffInfo = entry.getValue();
            String key = entry.getKey();

            String statusName = diffInfo.getStatusName();
            String oldValue = diffInfo.getOldValue() == null ? "null" : diffInfo.getOldValue().toString();
            String newValue = diffInfo.getNewValue() == null ? "null" : diffInfo.getNewValue().toString();

            switch (statusName) {
                case Status.DELETED -> diffResult.append("  ").append("- ").append(key).append(": ")
                        .append(oldValue)
                        .append("\n");
                case Status.UNCHANGED -> diffResult.append("    ").append(key).append(": ")
                        .append(oldValue)
                        .append("\n");
                case Status.UPDATED -> {
                    diffResult.append("  ").append("- ").append(key).append(": ")
                            .append(oldValue)
                            .append("\n");
                    diffResult.append("  ").append("+ ").append(key).append(": ")
                            .append(newValue)
                            .append("\n");
                }
                case Status.ADDED -> diffResult.append("  ").append("+ ").append(key).append(": ")
                        .append(newValue)
                        .append("\n");
                default -> throw new IllegalStateException("Unknown status name: " + statusName);
            }
        }
        diffResult.append("}");

        return diffResult.toString();
    }
}
