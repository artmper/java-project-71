package hexlet.code;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2, String formatName) throws IOException {
        Map<String, List<Object>> diff = makeDiff(filepath1, filepath2);
        try {
            return Formatter.formateDiff(diff, formatName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, List<Object>> makeDiff(String filepath1, String filepath2) throws IOException {
        Map<String, Object> firstFileData;
        Map<String, Object> secondFileData;

        try {
            firstFileData = Parser.getData(filepath1);
            secondFileData = Parser.getData(filepath2);
        } catch (Exception e) {
            throw new IOException(e);
        }

        Map<String, Object> mergedMap = new HashMap<>(firstFileData);
        mergedMap.putAll(secondFileData);
        Map<String, Object> sortedMap = Utils.sort(mergedMap);
        Map<String, List<Object>> diff = new LinkedHashMap<>();

        for (var entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue() == null ? "null" : entry.getValue();
            Object firstFileValue = firstFileData.get(key) == null ? "null" : firstFileData.get(key);
            List<Object> diffInfo = new ArrayList<>();

            if (!secondFileData.containsKey(key)) {
                diffInfo.addAll(List.of("removed", value));
                diff.put(key, diffInfo);
            } else if (firstFileData.containsKey(key)) {
                if (firstFileValue.equals(value)) {
                    diffInfo.addAll(List.of("unchanged", value));
                    diff.put(key, diffInfo);
                } else {
                    diffInfo.addAll(List.of("updated", firstFileValue, value));
                    diff.put(key, diffInfo);
                }
            } else {
                diffInfo.addAll(List.of("added", value));
                diff.put(key, diffInfo);
            }
        }

        return diff;
    }
}
