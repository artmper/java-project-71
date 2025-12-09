package hexlet.code;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @Test
    void testGenerate() throws Exception {
        var map1 = Parser.getData("src/test/resources/fixtures/file1.json");
        var map2 = Parser.getData("src/test/resources/fixtures/file2.json");
        var map3 = Parser.getData("src/test/resources/fixtures/file1.yaml");
        var map4 = Parser.getData("src/test/resources/fixtures/file2.yaml");

        String actual1 = Differ.generate(map1, map2);
        String actual2 = Differ.generate(map3, map4);
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

}
