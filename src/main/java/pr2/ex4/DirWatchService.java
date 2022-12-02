package pr2.ex4;

import pr2.ex3.Sum;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

public class DirWatchService {
    private String dir;
    private Map<String, List<String>> content;

    private void onCreate(WatchEvent watchEvent){
        System.out.println("\tCreated file: " + watchEvent.context());
        addEntry(Path.of(dir + watchEvent.context()));
    }

    private void onModify(WatchEvent watchEvent){
        String file = dir + watchEvent.context();
        System.out.println("\tModified file: " + watchEvent.context());

        try {
            List<String> a = Files.readAllLines(Path.of(file));
            List<String> b = this.content.get(Path.of(file).getFileName().toString());

            List<String> diff = new ArrayList<>(a);
            diff.removeAll(b);
            System.out.println("\t\tAdded strings: " + diff);

            List<String> diff2 = new ArrayList<>(b);
            diff2.removeAll(a);
            System.out.println("\t\tDeleted strings: " + diff2);

            this.content.put(Path.of(file).getFileName().toString(), a);
        } catch (IOException e) {
            System.out.println("Произошла ошибка в процессе обработки измененного файла " + Path.of(dir + watchEvent.context()).getFileName().toString());
            e.printStackTrace();
        }
    }

    private void onDelete(WatchEvent watchEvent){
        String file = dir + watchEvent.context();
        System.out.println("\tDeleted file: " + watchEvent.context());

        try {
            Path temp = Files.createTempFile(null, null);
            Files.write(temp, this.content.get(Path.of(file).getFileName().toString()));
            Map<String, String> result = new Sum().sum(temp.toFile());
//            Files.deleteIfExists(temp);
            for (Map.Entry<String,String> entry: result.entrySet()){
                System.out.println("\t\t" + entry.getKey());
                System.out.println("\t\t\t" + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка в процессе обработки удаленного файла " + Path.of(file).getFileName().toString());
            e.printStackTrace();
        }

        this.content.remove(Path.of(file).getFileName().toString());
    }

    private void addEntry(Path entry) {
        try {
            this.content.put(entry.getFileName().toString(), Files.readAllLines(entry));
        } catch (IOException e) {
            System.out.println("Проблема с чтением " + entry);
        }
    }

    public DirWatchService(String dir) throws IOException, InterruptedException {
        this.dir = dir;
        this.content = new HashMap<>();

        try {
            Files.list(Path.of(this.dir)).forEach(p -> addEntry(p));
        } catch (IOException e) {
            System.out.println("Проблема с отслеживаемой директорией " + dir);
        }

        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(this.dir);

        path.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);

        WatchKey watchKey;

        while ((watchKey = watchService.take()) != null){
            for (WatchEvent watchEvent: watchKey.pollEvents()){
                if (watchEvent.kind().equals(ENTRY_CREATE))
                    this.onCreate(watchEvent);
                else if (watchEvent.kind().equals(ENTRY_MODIFY))
                    this.onModify(watchEvent);
                else if (watchEvent.kind().equals(ENTRY_DELETE))
                    this.onDelete(watchEvent);
            }
            watchKey.reset();
        }
    }
}
