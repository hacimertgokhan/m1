package io.github.hacimertgokhan; // Kendi paket adınıza göre düzenleyin

import io.github.hacimertgokhan.m1.console.CliConsole;
import io.github.hacimertgokhan.m1.core.Database;
import io.github.hacimertgokhan.m1.jdbc.M1Driver; // Driver sınıfını import et
import io.github.hacimertgokhan.m1.web.ConfigLoader;
import io.github.hacimertgokhan.m1.web.WebConsole;
import io.github.hacimertgokhan.m1.web.WebConsoleConfig;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("M1 Veritabanı Motoru Başlatılıyor...");

        // Veritabanı ve sürücü ayarları
        String dbFilePath = "./m1.db";
        String jdbcUrl = "jdbc:m1:file:" + dbFilePath;

        try {
            // Sürücüyü sisteme kaydediyoruz. Bu her iki mod için de gerekli.
            DriverManager.registerDriver(new M1Driver());

            // Argümanları kontrol et ve doğru modu seç
            if (args.length > 0 && "console".equalsIgnoreCase(args[0])) {
                // --- KOMUT SATIRI KONSOLU MODU ---
                System.out.println("Mod: Komut Satırı Konsolu");
                CliConsole cliConsole = new CliConsole(jdbcUrl);
                cliConsole.start();
            } else {
                // --- WEB KONSOLU MODU (VARSAYILAN) ---
                System.out.println("Mod: Web Konsolu (Varsayılan)");
                System.out.println("Komut satırı için 'console' argümanını kullanabilirsiniz.");

                // 1. Web konsolu ayarlarını yükle
                WebConsoleConfig config = ConfigLoader.loadConfig("options.m1.yaml");
                System.out.println("Ayarlar 'options.m1.yaml' dosyasından yüklendi.");

                // 2. Veritabanını başlat
                Database database = Database.getInstance(dbFilePath);

                // 3. Web konsolunu başlat
                WebConsole webConsole = new WebConsole();
                webConsole.start(database, config);

                System.out.println("\n>>> M1 Web Konsolu http://localhost:5807 adresinde başlatıldı <<<");
                System.out.println("Kullanıcı Adı: " + config.getUsername());
            }
        } catch (Exception e) {
            System.err.println("M1 başlatılırken kritik bir hata oluştu!");
            e.printStackTrace();
        }
    }
}