package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;

import java.util.Map;


public final class Parser {

    private Parser() { }

    public static Map<String, Object> parse(String content, String format) throws IOException {
        ObjectMapper mapper;

        if (format.equals("yaml") || format.equals("yml")) {
            mapper = new ObjectMapper(new YAMLFactory());
        } else if (format.equals("json")) {
            mapper = new ObjectMapper();
        } else {
            throw new IllegalArgumentException("Unknown format: " + format);
        }

        return mapper.readValue(content, new TypeReference<>() { });
    }
}
