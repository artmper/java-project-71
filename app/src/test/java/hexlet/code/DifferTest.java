package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static String pathToJson1;
    private static String pathToJson2;
    private static String pathToYaml1;
    private static String pathToYaml2;
    private static Path pathToDiff;

    @BeforeAll
    public static void beforeAll() {
        pathToJson1 = getFixturePath("file1.json").toString();
        pathToJson2 = getFixturePath("file2.json").toString();
        pathToYaml1 = getFixturePath("file1.yaml").toString();
        pathToYaml2 = getFixturePath("file2.yaml").toString();
        pathToDiff = getFixturePath("diff1-2.json");
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    @Test
    void testGenerateStylish() throws Exception {
        String actual1 = Differ.generate(pathToJson1, pathToJson2, "stylish");
        String actual2 = Differ.generate(pathToYaml1, pathToYaml2, "stylish");

        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
    }

    @Test
    void testGeneratePlain() throws Exception {
        String actual1 = Differ.generate(pathToJson1, pathToJson2, "plain");
        String actual2 = Differ.generate(pathToYaml1, pathToYaml2, "plain");

        String expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";
        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
    }

    @Test
    void testGenerateJson() throws Exception {
        String actual1 = Differ.generate(pathToJson1, pathToJson2, "json");
        String actual2 = Differ.generate(pathToYaml1, pathToYaml2, "json");

        String expected = Files.readString(pathToDiff).trim();
        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
    }
}
