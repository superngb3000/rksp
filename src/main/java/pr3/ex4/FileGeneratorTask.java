package pr3.ex4;

import io.reactivex.rxjava3.core.Emitter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileGeneratorTask implements Runnable{
    private String fileName;
    private String dir;
    private Emitter emitter;

    public FileGeneratorTask(String fileName, String dir, Emitter emitter) {
        this.fileName = fileName;
        this.dir = dir;
        this.emitter = emitter;
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
            emitter.onNext(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
