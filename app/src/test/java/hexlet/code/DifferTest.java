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
    private static Path pathToJsonDiff;
    private static Path pathToPlainDiff;
    private static Path pathToStylishDiff;

    @BeforeAll
    static void beforeAll() {
        pathToJson1 = getFixturePath("file1.json").toString();
        pathToJson2 = getFixturePath("file2.json").toString();
        pathToYaml1 = getFixturePath("file1.yaml").toString();
        pathToYaml2 = getFixturePath("file2.yaml").toString();
        pathToJsonDiff = getFixturePath("json-diff.json");
        pathToPlainDiff = getFixturePath("plain-diff.txt");
        pathToStylishDiff = getFixturePath("stylish-diff.txt");
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    @Test
    void testGenerateStylish() throws Exception {
        String actual1 = Differ.generate(pathToJson1, pathToJson2, "stylish");
        String actual2 = Differ.generate(pathToYaml1, pathToYaml2, "stylish");

        String expected = Files.readString(pathToStylishDiff).trim();
        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
    }

    @Test
    void testGeneratePlain() throws Exception {
        String actual1 = Differ.generate(pathToJson1, pathToJson2, "plain");
        String actual2 = Differ.generate(pathToYaml1, pathToYaml2, "plain");

        String expected = Files.readString(pathToPlainDiff).trim();
        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
    }

    @Test
    void testGenerateJson() throws Exception {
        String actual1 = Differ.generate(pathToJson1, pathToJson2, "json");
        String actual2 = Differ.generate(pathToYaml1, pathToYaml2, "json");

        String expected = Files.readString(pathToJsonDiff).trim();
        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
    }

    @Test
    void testGenerateDefault() throws Exception {
        String actual1 = Differ.generate(pathToJson1, pathToJson2);
        String actual2 = Differ.generate(pathToYaml1, pathToYaml2);

        String expected = Files.readString(pathToStylishDiff).trim();
        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
    }
}
