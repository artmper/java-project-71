package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
    public static Map<String, Object> sort(Map<String, Object> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
