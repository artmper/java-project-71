package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DiffMaker {
    public static Map<String, Status> makeDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> set = new TreeSet<>(map1.keySet());
        Set<String> set2 = new TreeSet<>(map2.keySet());
        set.addAll(set2);

        Map<String, Status> diff = new LinkedHashMap<>();

        for (String key : set) {
            boolean key1Exists = map1.containsKey(key);
            boolean key2Exists = map2.containsKey(key);

            Object v1 = map1.get(key);
            Object v2 = map2.get(key);

            if (key1Exists && !key2Exists) {
                diff.put(key, new Status(Status.DELETED, v1, v2));
            } else if (key1Exists && v1 != null && v1.equals(v2)) {
                diff.put(key, new Status(Status.UNCHANGED, v1, v2));
            } else if (key1Exists && (v1 == null || !v1.equals(v2))) {
                diff.put(key, new Status(Status.UPDATED, v1, v2));
            } else if (!key1Exists && key2Exists) {
                diff.put(key, new Status(Status.ADDED, v1, v2));
            }
        }

        return diff;
    }
}
