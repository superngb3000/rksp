package pr2.ex1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class FileInteractor {
    private String dir = "src/main/java/pr2/ex1/";
    private final String defaultFileName = "readme.txt";
    private final String defaultText =
                """
                Hi,
                This is the first created fie.
                In order to write int it, read it or delete it use methods:
                - Path writeInFile(String file, String text)
                    - file - file path
                    - text - text to write
                - List<String> readFile(String file)
                    - file - file path
                - deleteFile(String file)
                    - file - file path
                """;

    public Path createFile(String file) throws IOException {
        return Files.createFile(Path.of(file));
    }

    public Path writeInFile(String file, String text) throws IOException {
        return Files.writeString(Path.of(file), text);
    }

    public List<String> readFile(String file) throws IOException {
        return Files.readAllLines(Path.of(file));
    }

    public void deleteFile(String file) throws IOException {
        Files.delete(Path.of(file));
    }

    public void waiter(String waitString){
        String check = waitString + " ";
        Scanner scanner = new Scanner(System.in);
        while (!check.equals(waitString)){
            check = scanner.nextLine();
        }
    }

    public FileInteractor() throws IOException {
        Path path = this.createFile(dir + defaultFileName);
        this.writeInFile(dir + defaultFileName, defaultText);
        List<String> output = this.readFile(dir + defaultFileName);
        for (String line: output){
            System.out.println(line);
        }
        this.waiter(" ");
        this.deleteFile(dir + defaultFileName);
    }

    public FileInteractor(String dir) throws IOException {
        this.dir = dir;

        Path path = this.createFile(dir + defaultFileName);
        this.writeInFile(dir + defaultFileName, defaultText);
        List<String> output = this.readFile(dir + defaultFileName);
        for (String line: output){
            System.out.println(line);
        }
        this.waiter(" ");
        this.deleteFile(dir + defaultFileName);
    }
}
