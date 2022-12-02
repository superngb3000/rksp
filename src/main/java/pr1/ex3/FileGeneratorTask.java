package pr1.ex3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.BlockingQueue;

public class FileGeneratorTask implements Runnable{
    private String fileName;
    private String dir;
    private BlockingQueue<File> fileBlockingQueue;

    public FileGeneratorTask(String fileName, String dir, BlockingQueue<File> fileBlockingQueue) {
        this.fileName = fileName;
        this.dir = dir;
        this.fileBlockingQueue = fileBlockingQueue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int)(Math.random() * 900 + 100));
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        File file;

        int fileType = (int)(Math.random() * FileTypeEnum.values().length);
        synchronized (Thread.currentThread()) {
            file = new File(dir + fileName + "." + FileTypeEnum.values()[fileType]);
        }

        try{
            file.createNewFile();
            System.out.println("\tСоздан " + file.getName());
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.setLength((int)(Math.random() * 90 + 10));
            fileBlockingQueue.put(file);
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
