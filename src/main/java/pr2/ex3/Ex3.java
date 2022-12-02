package pr2.ex3;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Ex3 {

    public Ex3() {
        System.out.println("-==3==-");

        String dir = "src/main/java/pr2/ex3/";
        String fileName = "source.txt";

        Map<String, String> result;

        File file = new File(dir + fileName);
        try {
            result = new Sum().sum(file);

            for (Map.Entry<String,String> entry: result.entrySet()){
                System.out.println("\t" + entry.getKey());
                System.out.println("\t\t" + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
