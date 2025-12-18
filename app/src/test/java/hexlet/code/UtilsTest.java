package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilsTest {

    @Test
    void testUtilsSort() throws Exception {
        Map<String, Object> actual1 = Utils.sort(Parser.getData("src/test/resources/fixtures/file1.json"));
        Map<String, Object> expected1 = Parser.getData("src/test/resources/fixtures/sortedFile1.json");
        Map<String, Object> actual2 = Utils.sort(Parser.getData("src/test/resources/fixtures/file2.json"));
        Map<String, Object> expected2 = Parser.getData("src/test/resources/fixtures/sortedFile2.json");

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    void testUtilsStringify() {
        String actual1 = Utils.stringify(1);
        String actual2 = Utils.stringify(List.of(1, 2));
        String actual3 = Utils.stringify(true);
        String actual4 = Utils.stringify('c');
        String actual5 = Utils.stringify(null);

        assertEquals("1", actual1);
        assertEquals("[complex value]", actual2);
        assertEquals("true", actual3);
        assertEquals("c", actual4);
        assertEquals("null", actual5);
    }
}
