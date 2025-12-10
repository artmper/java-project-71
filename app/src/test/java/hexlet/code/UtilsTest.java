package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
    void testUtilsIsPrimitive() {
        boolean actual1 = Utils.isPrimitive(12);
        boolean actual2 = Utils.isPrimitive(List.of(1, 2, 3));
        boolean actual3 = Utils.isPrimitive(true);
        boolean actual4 = Utils.isPrimitive('c');

        assertTrue(actual1);
        assertFalse(actual2);
        assertTrue(actual3);
        assertTrue(actual4);
    }
}
