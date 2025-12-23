package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.NoSuchElementException;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        ObjectMapper mapper;
        Path pathToFirstFile = Paths.get(filePath1).toAbsolutePath().normalize();
        Path pathToSecondFile = Paths.get(filePath2).toAbsolutePath().normalize();

        String fileExtension1 = getFileExtension(pathToFirstFile);
        String fileExtension2 = getFileExtension(pathToSecondFile);

        String firstFileData = Files.readString(pathToFirstFile);
        String secondFileData = Files.readString(pathToSecondFile);

        if ((fileExtension1.equals("yml") || fileExtension1.equals("yaml"))
                && (fileExtension2.equals("yml") || fileExtension2.equals("yaml"))) {
            mapper = new ObjectMapper(new YAMLFactory());
        } else if (fileExtension1.equals("json") && fileExtension2.equals("json")) {
            mapper = new ObjectMapper();
        } else {
            throw new IllegalArgumentException("Files extensions doesn't match, or not suitable: "
                    + fileExtension1 + " " + fileExtension2);
        }

        Map<String, Object> map1 = Parser.parse(firstFileData, mapper);
        Map<String, Object> map2 = Parser.parse(secondFileData, mapper);

        Map<String, Status> diff = DiffMaker.makeDiff(map1, map2);
        String formattedDiff = Formatter.formateDiff(diff, formatName);

        System.out.println(formattedDiff);

        return formattedDiff;
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String getFileExtension(Path filePath) throws IOException {
        String fileExtension;

        if (!Files.exists(filePath)) {
            throw new IOException("File '" + filePath + "' does not exist");
        }

        String fileName = filePath.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex == -1) {
            throw new NoSuchElementException("The specified file does not have an extension.");
        } else {
            fileExtension = fileName.substring(dotIndex + 1);
        }

        return fileExtension;
    }
}
