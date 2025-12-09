package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {

    private static Map<String, Object> map1;
    private static Map<String, Object> map2;
    private static Map<String, Object> map3;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }
    @BeforeAll
    static void beforeAll() {
        map1 = Map.of("host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false);
        map2 = Map.of(
                "timeout", 20,
                "verbose", true,
                "host", "hexlet.io");
        map3 = Map.of(
                "timeout", 20,
                "verbose", true,
                "host", "hexlet.io",
                "author", "Artem");

    }
    @Test
    void testGetData() throws Exception {
        Path jsonPath1 = getFixturePath("file1.json");
        Path jsonPath2 = getFixturePath("file2.json");
        Path jsonPath3 = getFixturePath("file99.json");
        Path yamlPath1 = getFixturePath("file1.yaml");
        Path yamlPath2 = getFixturePath("file2.yaml");

        Map<String, Object> actual1 = Parser.getData(String.valueOf(jsonPath1));
        Map<String, Object> actual2 = Parser.getData(String.valueOf(jsonPath2));
        Map<String, Object> actual3 = Parser.getData(String.valueOf(yamlPath1));
        Map<String, Object> actual4 = Parser.getData(String.valueOf(yamlPath2));
        assertEquals(map1, actual1);
        assertEquals(map2, actual2);
        assertEquals(map1, actual3);
        assertEquals(map2, actual4);
        assertThrows(Exception.class, () -> Parser.getData(String.valueOf(jsonPath3)));
    }

    @Test
    void testGenerate() {
        String actual1 = Differ.generate(map1, map2);
        String actual2 = Differ.generate(map1, map3);
        String expected1 = """
                    {
                      - follow: false
                        host: hexlet.io
                      - proxy: 123.234.53.22
                      - timeout: 50
                      + timeout: 20
                      + verbose: true
                    }""";
        String expected2 = """
                    {
                      + author: Artem
                      - follow: false
                        host: hexlet.io
                      - proxy: 123.234.53.22
                      - timeout: 50
                      + timeout: 20
                      + verbose: true
                    }""";
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    void testUtils() {
        Map<String, Object> actual1 = Utils.sort(map1);
        Map<String, Object> actual2 = Utils.sort(map3);

        var expected1 = Map.of("follow", false,
                "host", "hexlet.io",
                "proxy", "123.234.53.22",
                "timeout", 50);
        var expected2 = Map.of("author", "Artem",
                "host", "hexlet.io",
                "timeout", 20,
                "verbose", true);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }
}
