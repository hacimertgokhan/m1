package io.github.hacimertgokhan.m1.console;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {

    private static final String DB_URL = "jdbc:m1:file:./m1.db";

    public void start() {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connection successfull. Exit with 'exit'.");

            while (true) {
                System.out.print("m1-sql> ");
                String sql = scanner.nextLine();

                if (sql.equalsIgnoreCase("exit")) {
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
                        System.out.println("Success. " + affectedRows + " lines effected.");
                    }
                } catch (SQLException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("M1 Console shutted down.");
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
                row.add(rs.getString(i));
            }
            System.out.println(String.join(" | ", row));
            rowCount++;
        }
        System.out.println("\n(" + rowCount + " lines found)");
    }
}
