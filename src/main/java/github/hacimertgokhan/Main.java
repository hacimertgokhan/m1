package github.hacimertgokhan;

import github.hacimertgokhan.m1.console.Console;
import github.hacimertgokhan.m1.jdbc.M1Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("M1 Veritabanı Konsoluna Hoş Geldiniz!");
        System.out.println("JDBC Sürücüsü Yükleniyor...");

        try {
            // Bu sayede DriverManager.getConnection("jdbc:m1:...") çağrısı bizim driver'ımızı bulacak.
            DriverManager.registerDriver(new M1Driver());
            System.out.println("JDBC Sürücüsü başarıyla yüklendi.");

            // Konsolu başlat
            Console console = new Console();
            console.start();

        } catch (SQLException e) {
            System.err.println("JDBC sürücüsü yüklenirken bir hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}