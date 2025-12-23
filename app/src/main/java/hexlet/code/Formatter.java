package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;

import java.util.Map;

public class Formatter {
    public static String formateDiff(Map<String, Status> diff, String format) throws Exception {

        return switch (format) {
            case "stylish" -> Stylish.formate(diff);
            case "plain" -> Plain.formate(diff);
            case "json" -> Json.formate(diff);
            default -> throw new Exception("Unknown format type: " + format);
        };
    }
}

