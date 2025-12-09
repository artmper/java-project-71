package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    private static Path path;

    public static String getFileExtension(String filePath) throws Exception {
        path = Paths.get(filePath).toAbsolutePath().normalize();
        String fileExtension;

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex == -1) {
            throw new Exception("The specified file does not have an extension.");
        } else {
            fileExtension = fileName.substring(dotIndex + 1);
        }

        return fileExtension;
    }

    public static Map<String, Object> getData(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String fileExtension = getFileExtension(filePath);
        String fileContent = Files.readString(path);

        if (fileExtension.equals("yml") || fileExtension.equals("yaml")) {
            mapper = new ObjectMapper(new YAMLFactory());
        }

        return mapper.readValue(fileContent, new TypeReference<Map<String, Object>>() { });
    }
}
