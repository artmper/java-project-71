package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {
    private static final Map<String, Object> MAP1 = new LinkedHashMap<>();
    private static final Map<String, Object> MAP2 = new LinkedHashMap<>();

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    @BeforeAll
    static void beforeAll() {
        MAP1.put("setting1", "Some value");
        MAP1.put("setting2", 200);
        MAP1.put("setting3", true);
        MAP1.put("key1", "value1");
        MAP1.put("numbers1", List.of(1, 2, 3, 4));
        MAP1.put("numbers2", List.of(2, 3, 4, 5));
        MAP1.put("id", 45);
        MAP1.put("default", null);
        MAP1.put("checked", false);
        MAP1.put("numbers3", List.of(3, 4, 5));
        MAP1.put("chars1", List.of("a", "b", "c"));
        MAP1.put("chars2", List.of("d", "e", "f"));

        MAP2.put("setting1", "Another value");
        MAP2.put("setting2", 300);
        MAP2.put("setting3", "none");
        MAP2.put("key2", "value2");
        MAP2.put("numbers1", List.of(1, 2, 3, 4));
        MAP2.put("numbers2", List.of(22, 33, 44, 55));
        MAP2.put("id", null);
        MAP2.put("default", List.of("value1", "value2"));
        MAP2.put("checked", true);
        MAP2.put("numbers4", List.of(4, 5, 6));
        MAP2.put("chars1", List.of("a", "b", "c"));
        MAP2.put("chars2", false);
        MAP2.put("obj1", Map.of("nestedKey", "value", "isNested", true));

    }

    @Test
    void testGetDataJson() throws Exception {
        Path jsonPath1 = getFixturePath("file1.json");
        Path jsonPath2 = getFixturePath("file2.json");
        Path jsonPath3 = getFixturePath("file99.json");

        Map<String, Object> actual1 = Parser.getData(String.valueOf(jsonPath1));
        Map<String, Object> actual2 = Parser.getData(String.valueOf(jsonPath2));

        assertEquals(MAP1, actual1);
        assertEquals(MAP2, actual2);
        assertThrows(Exception.class, () -> Parser.getData(String.valueOf(jsonPath3)));
    }

    @Test
    void testGetDataYaml() throws Exception {
        Path yamlPath1 = getFixturePath("file1.yaml");
        Path yamlPath2 = getFixturePath("file2.yaml");

        Map<String, Object> actual1 = Parser.getData(String.valueOf(yamlPath1));
        Map<String, Object> actual2 = Parser.getData(String.valueOf(yamlPath2));

        assertEquals(MAP1, actual1);
        assertEquals(MAP2, actual2);

    }
}
