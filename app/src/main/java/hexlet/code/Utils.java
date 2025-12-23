//package hexlet.code;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//public class Utils {
//
//    public static Map<String, Object> sort(Map<String, Object> map) {
//        Map<String, Object> sortedMap = new LinkedHashMap<>();
//        map.entrySet().stream()
//                .sorted(Map.Entry.comparingByKey())
//                .forEachOrdered(entry -> sortedMap.put(entry.getKey(), entry.getValue()));
//        return sortedMap;
//    }
//}
