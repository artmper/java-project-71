package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {

    private static Map<String, Object> json1;
    private static Map<String, Object> json2;
    private static Map<String, Object> json3;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }
    @BeforeAll
    static void beforeAll() {
        json1 = Map.of("host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false);
        json2 = Map.of(
                "timeout", 20,
                "verbose", true,
                "host", "hexlet.io");
        json3 = Map.of(
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
        Map<String, Object> actual1 = Differ.getData(String.valueOf(jsonPath1));
        Map<String, Object> actual2 = Differ.getData(String.valueOf(jsonPath2));
        assertEquals(json1, actual1);
        assertEquals(json2, actual2);
        assertThrows(Exception.class, () -> Differ.getData(String.valueOf(jsonPath3)));
    }

    @Test
    void testGenerate() {
        String actual1 = Differ.generate(json1, json2);
        String actual2 = Differ.generate(json1, json3);
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
        Map<String, Object> actual1 = Utils.sort(json1);
        Map<String, Object> actual2 = Utils.sort(json3);

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
