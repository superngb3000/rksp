package pr2.ex3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

public class Sum {
    private int sum(ByteBuffer bb) {
        int sum = 0;
        while (bb.hasRemaining()) {
            if ((sum & 1) != 0)
                sum = (sum >> 1) + 0x8000;
            else
                sum >>= 1;
            sum += bb.get() & 0xff;
            sum &= 0xffff;
        }
        return sum;
    }

    public Map<String, String> sum(File file) throws IOException {
        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                FileChannel fileChannel = fileInputStream.getChannel()) {

            int size = (int) fileChannel.size();
            MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, size);

            int sum = sum(byteBuffer);
            int kb = (size + 1023) / 1024;

            Map<String, String> result = new HashMap<>();
            result.put("Файл", file.getPath());
            result.put("Размер файла, кб", Integer.toString(kb));
            result.put("Контрольная сумма", Integer.toString(sum));

            return result;
        }
    }
}
