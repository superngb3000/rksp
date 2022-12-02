package pr1.ex3;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex3 {
    private final String dir = "src/main/java/pr1/ex3/files/";

    private void F(){
        BlockingQueue<File> fileBlockingQueue = new ArrayBlockingQueue<>(5);
        ExecutorService fileGeneratorPool = Executors.newFixedThreadPool(8);
        ExecutorService fileExecutorPool = Executors.newFixedThreadPool(8);

        int fileCount = 0;
        String fileName = "";
        while(fileCount < 10){

            //fileGen
            fileName = Integer.toString(fileCount);
            FileGeneratorTask fileGeneratorTask = new FileGeneratorTask(fileName, dir, fileBlockingQueue);
            fileGeneratorPool.submit(fileGeneratorTask);

            //fileExec
            FileExecutorTask fileExecutorTask = new FileExecutorTask(fileBlockingQueue);
            fileExecutorPool.submit(fileExecutorTask);

            fileCount++;
        }

        fileExecutorPool.shutdown();
        fileGeneratorPool.shutdown();
    }

    public Ex3() {
        System.out.println("-==3==-");
        this.F();
    }
}
