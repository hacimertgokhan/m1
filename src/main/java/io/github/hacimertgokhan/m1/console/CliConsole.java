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
import static io.github.hacimertgokhan.m1.tools.M1Logger.*;

public class CliConsole {

    private final String dbUrl;

    public CliConsole(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void start() {
        
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Scanner scanner = new Scanner(System.in)) {

            info("Connection successfull. Write 'exit' to close connection.");

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
                        info("İşlem başarılı. Etkilenen satır sayısı: " + affectedRows);
                    }
                } catch (SQLException e) {
                    error("Hata: " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            error("Veritabanına bağlanılamadı: " + e.getMessage());
            e.printStackTrace();
        }
        info("M1 Komut Satırı Konsolu kapatıldı.");
    }

    private void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<String> headers = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            headers.add(metaData.getColumnName(i));
        }
        info(String.join(" | ", headers));
        info("-".repeat(String.join(" | ", headers).length()));

        int rowCount = 0;
        while (rs.next()) {
            List<String> row = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                Object value = rs.getObject(i);
                row.add(value == null ? "NULL" : value.toString());
            }
            info(String.join(" | ", row));
            rowCount++;
        }
        info("\n(" + rowCount + " satır bulundu)");
    }
}