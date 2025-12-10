package hexlet.code;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilsTest {

    @Test
    void testUtils() throws Exception {
        Map<String, Object> actual1 = Utils.sort(Parser.getData("src/test/resources/fixtures/file1.json"));
        Map<String, Object> expected1 = Parser.getData("src/test/resources/fixtures/sortedFile1.json");
        Map<String, Object> actual2 = Utils.sort(Parser.getData("src/test/resources/fixtures/file2.json"));
        Map<String, Object> expected2 = Parser.getData("src/test/resources/fixtures/sortedFile2.json");

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }
}
