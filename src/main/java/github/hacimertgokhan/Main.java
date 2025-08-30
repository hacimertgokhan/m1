package github.hacimertgokhan;

import github.hacimertgokhan.m1.console.Console;
import github.hacimertgokhan.m1.core.Database;
import github.hacimertgokhan.m1.jdbc.M1Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Loading JDBC Drivers...");

        try {
            DriverManager.registerDriver(new M1Driver());
            System.out.println("JDBC Drivers successfully loaded.");

            String dbUrl = "jdbc:m1:file:./m1.db";
            String dbFilePath = dbUrl.substring("jdbc:m1:file:".length());
            final Database database = Database.getInstance(dbFilePath);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    database.shutdown();
                } catch (SQLException e) {
                    System.err.println("An error occured: " + e.getMessage());
                }
            }));

            Console console = new Console();
            console.start();

        } catch (SQLException e) {
            System.err.println("Database cannot started: " + e.getMessage());
            e.printStackTrace();
        }
    }
}