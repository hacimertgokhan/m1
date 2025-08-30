package io.github.hacimertgokhan.m1.console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CliConsole {

    private final String dbUrl;

    public CliConsole(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void start() {
        // try-with-resources ile kaynakların otomatik kapanmasını sağlıyoruz
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Komut Satırı Konsoluna Hoş Geldiniz.");
            System.out.println("Bağlantı başarılı. 'exit' yazarak çıkabilirsiniz.");

            while (true) {
                System.out.print("m1-sql> ");
                String sql = scanner.nextLine();

                if (sql == null || "exit".equalsIgnoreCase(sql.trim())) {
                    break;
                }

                if (sql.trim().isEmpty()) {
                    continue;
                }

                try (Statement statement = connection.createStatement()) {
                    boolean isQuery = sql.trim().toLowerCase().startsWith("select");

                    if (isQuery) {
                        ResultSet rs = statement.executeQuery(sql);
                        printResultSet(rs);
                    } else {
                        int affectedRows = statement.executeUpdate(sql);
                        System.out.println("İşlem başarılı. Etkilenen satır sayısı: " + affectedRows);
                    }
                } catch (SQLException e) {
                    System.err.println("Hata: " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            System.err.println("Veritabanına bağlanılamadı: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("M1 Komut Satırı Konsolu kapatıldı.");
    }

    private void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<String> headers = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            headers.add(metaData.getColumnName(i));
        }
        System.out.println(String.join(" | ", headers));
        System.out.println("-".repeat(String.join(" | ", headers).length()));

        int rowCount = 0;
        while (rs.next()) {
            List<String> row = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                Object value = rs.getObject(i);
                row.add(value == null ? "NULL" : value.toString());
            }
            System.out.println(String.join(" | ", row));
            rowCount++;
        }
        System.out.println("\n(" + rowCount + " satır bulundu)");
    }
}