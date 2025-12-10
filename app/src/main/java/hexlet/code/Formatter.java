package hexlet.code;

import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formateDiff(Map<String, List<Object>> diff, String format) throws Exception {
        String formattedDiff;

        if (format.equals("stylish")) {
            formattedDiff = Stylish.formate(diff);
        } else if (format.equals("plain")) {
            formattedDiff = Plain.formate(diff);
        } else {
            throw new Exception("Unknown format type.");
        }

        return formattedDiff;
    }
}

