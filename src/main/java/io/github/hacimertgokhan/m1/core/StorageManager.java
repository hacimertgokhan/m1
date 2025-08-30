package io.github.hacimertgokhan.m1.core;


import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StorageManager {

    private final File dbFile;

    public StorageManager(String filePath) {
        this.dbFile = new File(filePath);
    }

    /**
     * Veritabanı durumunu (tabloları) dosyaya yazar.
     * @param tables Kaydedilecek tablo haritası.
     */
    public synchronized void save(Map<String, Table> tables) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dbFile))) {
            oos.writeObject(new ConcurrentHashMap<>(tables)); 
        }
    }

    /**
     * Veritabanı durumunu dosyadan okur. Dosya yoksa boş bir harita döndürür.
     * @return Dosyadan okunan tablo haritası.
     */
    @SuppressWarnings("unchecked")
    public synchronized Map<String, Table> load() throws IOException, ClassNotFoundException {
        if (!dbFile.exists()) {
            System.out.println("M1 database file cannot found, creating a new one: " + dbFile.getName());
            return new ConcurrentHashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dbFile))) {
            return (Map<String, Table>) ois.readObject();
        }
    }
}