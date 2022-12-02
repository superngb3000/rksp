package pr3.ex4;

import io.reactivex.rxjava3.core.Observable;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Ex4 {
    private final String dir = "src/main/java/pr3/ex4/files/";

    public Ex4() {
        System.out.println("-==4==-");

        BlockingQueue<File> fileBlockingQueue = new ArrayBlockingQueue<>(5);

        int fileCount = 0;
        String fileName = "";
        FileExecutorTask fileExecutorTask = new FileExecutorTask(fileBlockingQueue);
        while(fileCount < 10){
            fileName = Integer.toString(fileCount);
            String finalFileName = fileName;

            Observable.create(
                    tempEmitter -> new FileGeneratorTask(finalFileName, dir, tempEmitter).run())
                    .subscribe(e -> {
                        fileBlockingQueue.put((File) e);
                        fileExecutorTask.run();
                    });

            fileCount++;
        }
    }
}
