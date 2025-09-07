package io.github.hacimertgokhan; 

import io.github.hacimertgokhan.m1.console.CliConsole;
import io.github.hacimertgokhan.m1.core.Database;
import io.github.hacimertgokhan.m1.jdbc.M1Driver; 
import io.github.hacimertgokhan.m1.web.ConfigLoader;
import io.github.hacimertgokhan.m1.web.WebConsole;
import io.github.hacimertgokhan.m1.web.WebConsoleConfig;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {

        String dbFilePath = "./m1.db";
        String jdbcUrl = "jdbc:m1:file:" + dbFilePath;

        try {
            
            DriverManager.registerDriver(new M1Driver());

            
            if (args.length > 0 && "--console".equalsIgnoreCase(args[0])) {
                
                CliConsole cliConsole = new CliConsole(jdbcUrl);
                cliConsole.start();
            } else {
                
                WebConsoleConfig config = ConfigLoader.loadConfig();

                Database database = Database.getInstance(dbFilePath);

                
                WebConsole webConsole = new WebConsole();
                webConsole.start(database, config);

                System.out.println("\n>>> M1 Web Console: http://localhost:5807");
            }
        } catch (Exception e) {
            System.err.println("An error occured while starting!");
            e.printStackTrace();
        }
    }
}