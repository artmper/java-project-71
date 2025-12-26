package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;
import java.util.NoSuchElementException;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        Path pathToFirstFile = Paths.get(filePath1).toAbsolutePath().normalize();
        Path pathToSecondFile = Paths.get(filePath2).toAbsolutePath().normalize();

        String fileExtension1 = getFileExtension(pathToFirstFile);
        String fileExtension2 = getFileExtension(pathToSecondFile);

        String firstFileData = Files.readString(pathToFirstFile);
        String secondFileData = Files.readString(pathToSecondFile);

        Map<String, Object> map1 = Parser.parse(firstFileData, fileExtension1);
        Map<String, Object> map2 = Parser.parse(secondFileData, fileExtension2);

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
            throw new NoSuchElementException("The specified file does not have an extension: " + fileName);
        } else {
            fileExtension = fileName.substring(dotIndex + 1);
        }

        return fileExtension;
    }
}
