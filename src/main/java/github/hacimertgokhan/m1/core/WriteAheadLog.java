package github.hacimertgokhan.m1.core;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class WriteAheadLog {

    private final Path logFilePath;
    private BufferedWriter writer;

    public WriteAheadLog(String dbFilePath) throws IOException {
        this.logFilePath = Paths.get(dbFilePath + ".wal");
        this.writer = Files.newBufferedWriter(logFilePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    /**
     * Gelen SQL komutunu log dosyasına yazar.
     * @param sql Loglanacak SQL komutu.
     */
    public synchronized void log(String sql) throws IOException {
        writer.write(sql);
        writer.newLine();
        writer.flush(); 
    }

    /**
     * Veritabanı başlangıcında log dosyasını okuyarak kurtarma yapar.
     * @return Kurtarılacak SQL komutlarının listesi.
     */
    public List<String> recover() throws IOException {
        if (!Files.exists(logFilePath)) {
            return List.of(); 
        }
        return Files.readAllLines(logFilePath).stream()
                .filter(line -> !line.isBlank())
                .collect(Collectors.toList());
    }

    /**
     * Checkpoint sonrası log dosyasını temizler.
     */
    public synchronized void clear() throws IOException {
        
        writer.close();
        writer = Files.newBufferedWriter(logFilePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.APPEND);
    }

    public synchronized void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}