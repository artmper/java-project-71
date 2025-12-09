package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    private static final Map<String, Object> map1 = new LinkedHashMap<>();
    private static final Map<String, Object> map2 = new LinkedHashMap<>();

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }
    @BeforeAll
    static void beforeAll() {
        map1.put("setting1", "Some value");
        map1.put("setting2", 200);
        map1.put("setting3", true);
        map1.put("key1", "value1");
        map1.put("numbers1", List.of(1, 2, 3, 4));
        map1.put("numbers2", List.of(2, 3, 4, 5));
        map1.put("id", 45);
        map1.put("default", null);
        map1.put("checked", false);
        map1.put("numbers3", List.of(3, 4, 5));
        map1.put("chars1", List.of("a", "b", "c"));
        map1.put("chars2", List.of("d", "e", "f"));

        map2.put("setting1", "Another value");
        map2.put("setting2", 300);
        map2.put("setting3", "none");
        map2.put("key2", "value2");
        map2.put("numbers1", List.of(1, 2, 3, 4));
        map2.put("numbers2", List.of(22, 33, 44, 55));
        map2.put("id", null);
        map2.put("default", List.of("value1", "value2"));
        map2.put("checked", true);
        map2.put("numbers4", List.of(4, 5, 6));
        map2.put("chars1", List.of("a", "b", "c"));
        map2.put("chars2", false);
        map2.put("obj1", Map.of("nestedKey", "value", "isNested", true));

    }

    @Test
    void testGetDataJson() throws Exception {
        Path jsonPath1 = getFixturePath("file1.json");
        Path jsonPath2 = getFixturePath("file2.json");
        Path jsonPath3 = getFixturePath("file99.json");

        Map<String, Object> actual1 = Parser.getData(String.valueOf(jsonPath1));
        Map<String, Object> actual2 = Parser.getData(String.valueOf(jsonPath2));

        assertEquals(map1, actual1);
        assertEquals(map2, actual2);
        assertThrows(Exception.class, () -> Parser.getData(String.valueOf(jsonPath3)));
    }

    @Test
    void testGetDataYaml() throws Exception {
        Path yamlPath1 = getFixturePath("file1.yaml");
        Path yamlPath2 = getFixturePath("file2.yaml");

        Map<String, Object> actual1 = Parser.getData(String.valueOf(yamlPath1));
        Map<String, Object> actual2 = Parser.getData(String.valueOf(yamlPath2));

        assertEquals(map1, actual1);
        assertEquals(map2, actual2);

    }
}
