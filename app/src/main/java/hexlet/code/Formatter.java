package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public class Formatter {
    public static String formateDiff(Map<String, Status> diff, String format)
            throws IllegalArgumentException, JsonProcessingException {

        return switch (format) {
            case "stylish" -> Stylish.formate(diff);
            case "plain" -> Plain.formate(diff);
            case "json" -> Json.formate(diff);
            default -> throw new IllegalArgumentException("Unknown format type: " + format);
        };
    }
}

