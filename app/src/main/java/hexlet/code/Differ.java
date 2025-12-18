package hexlet.code;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2, String formatName) throws Exception {
        Map<String, List<Object>> diff = makeDiff(filepath1, filepath2);

        return Formatter.formateDiff(diff, formatName);

    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, List<Object>> diff = makeDiff(filepath1, filepath2);

        return Formatter.formateDiff(diff, "stylish");

    }

    public static Map<String, List<Object>> makeDiff(String filepath1, String filepath2) throws IOException {
        Map<String, Object> firstFileData = Parser.getData(filepath1);
        Map<String, Object> secondFileData = Parser.getData(filepath2);

        Map<String, Object> mergedMap = new HashMap<>(firstFileData);
        mergedMap.putAll(secondFileData);
        Map<String, Object> sortedMap = Utils.sort(mergedMap);
        Map<String, List<Object>> diff = new LinkedHashMap<>();

        for (var entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            List<Object> diffInfo = new ArrayList<>();

            if (!secondFileData.containsKey(key)) {
                diffInfo.add("removed");
                diffInfo.add(value);
                diff.put(key, diffInfo);
            } else if (firstFileData.containsKey(key)) {
                value = entry.getValue() == null ? "null" : entry.getValue();
                Object firstFileValue = firstFileData.get(key) == null ? "null" : firstFileData.get(key);
                if (firstFileValue.equals(value)) {
                    diffInfo.add("unchanged");
                } else {
                    diffInfo.add("updated");
                    diffInfo.add(firstFileData.get(key));
                }
                diffInfo.add(entry.getValue());
                diff.put(key, diffInfo);
            } else {
                diffInfo.add("added");
                diffInfo.add(entry.getValue());
                diff.put(key, diffInfo);
            }
        }

        return diff;
    }
}
