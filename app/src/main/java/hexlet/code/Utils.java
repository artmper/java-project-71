package hexlet.code;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static Map<String, Object> sort(Map<String, Object> map) {
        Map<String, Object> sortedMap = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(entry -> sortedMap.put(entry.getKey(), entry.getValue()));
        return sortedMap;
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
