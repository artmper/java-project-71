package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;

public class Utils {
    public static Map<String, Object> sort(Map<String, Object> map) {
        Map<String, Object> sortedMap = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(entry -> sortedMap.put(entry.getKey(), entry.getValue()));
        return sortedMap;
    }

    public static boolean isPrimitive(Object value) {
        if (value == null) {
            return false;
        }

        return value instanceof Number
                || value instanceof Boolean
                || value instanceof Character;
    }
}
