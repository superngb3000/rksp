package pr3.ex4;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class FileExecutorTask implements  Runnable{
    private BlockingQueue<File> fileBlockingQueue;

    public FileExecutorTask(BlockingQueue<File> fileBlockingQueue) {
        this.fileBlockingQueue = fileBlockingQueue;
    }

    @Override
    public void run() {
        try {
            File file;
            file = fileBlockingQueue.take();
            Thread.sleep(file.length() * 7);
            System.out.println("\tОбработан " + file.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
