package pr2.ex2;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;


public class Ex2 {

    private void F1(File source, File destination) throws IOException {
        FileInputStream  fileInputStream = new FileInputStream(source);
        FileOutputStream fileOutputStream = new FileOutputStream(destination);

        byte[] buffer = new byte[1024*1024];
        int length;

        while ((length = fileInputStream.read(buffer)) > 0){
            fileOutputStream.write(buffer, 0, length);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }

    private void F2(File source, File destination) throws IOException {
        FileChannel sourceChannel = new FileInputStream(source).getChannel();
        FileChannel destinationChannel = new FileOutputStream(destination).getChannel();

        destinationChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

        sourceChannel.close();
        destinationChannel.close();
    }

    private void F3(File source, File destination) throws IOException {
        FileUtils.copyFile(source, destination);
    }

    private void F4(File source, File destination) throws IOException {
        Files.copy(source.toPath(), destination.toPath());
    }

    public Ex2() throws IOException {
        System.out.println("-==2==-");

        String dir = "src/main/java/pr2/ex2/";
        String file = "debian-11.5.0-amd64-netinst.iso.txt";

        File source = new File(dir + file);
        File destination = new File(dir + "copy_" + file);

        System.out.println("\tFileInputStream/FileOutputStream");
        long start = System.currentTimeMillis();
        this.F1(source, destination);
        long end = System.currentTimeMillis();
        System.out.println("\t\tВремя выполнение: " + (end - start));
        destination.delete();

        System.out.println("\tFileChannel");
        start = System.currentTimeMillis();
        this.F2(source, destination);
        end = System.currentTimeMillis();
        System.out.println("\t\tВремя выполнение: " + (end - start));
        destination.delete();

        System.out.println("\tApache Commons IO");
        start = System.currentTimeMillis();
        this.F3(source, destination);
        end = System.currentTimeMillis();
        System.out.println("\t\tВремя выполнение: " + (end - start));
        destination.delete();

        System.out.println("\tFiles class");
        start = System.currentTimeMillis();
        this.F4(source, destination);
        end = System.currentTimeMillis();
        System.out.println("\t\tВремя выполнение: " + (end - start));
        destination.delete();
    }
}
