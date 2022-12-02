package pr2.ex4;

import java.io.IOException;

public class Ex4 {

    public Ex4() {
        System.out.println("-==4==-");

        String dir = "src/main/java/pr2/ex4/";

        try {
            new DirWatchService(dir);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
