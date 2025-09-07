package io.github.hacimertgokhan.m1.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class M1Logger {

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    private static final String BG_RED = "\u001B[41m";
    private static final String BOLD = "\u001B[1m";


    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static boolean showTimestamp = true;
    private static boolean showIcons = true;
    private static String appName = "M1";

    private static void log(LogLevel level, String message, Object... args) {
        StringBuilder logBuilder = new StringBuilder();

        if (showTimestamp) {
            String timestamp = LocalDateTime.now().format(TIME_FORMAT);
            logBuilder.append(CYAN).append("[").append(timestamp).append("]").append(RESET).append(" ");
        }

        logBuilder.append(PURPLE).append("[").append(appName).append("]").append(RESET).append(" ");

        logBuilder.append(level.color).append(BOLD).append("[")
                .append(level.name()).append("]").append(RESET).append(" ");

        if (showIcons) {
            logBuilder.append(level.icon).append(" ");
        }

        String formattedMessage = String.format(message, args);
        logBuilder.append(level.color).append(formattedMessage).append(RESET);

        System.out.println(logBuilder.toString());
    }

    public static void debug(String message, Object... args) {
        log(LogLevel.DEBUG, message, args);
    }

    public static void info(String message, Object... args) {
        log(LogLevel.INFO, message, args);
    }

    public static void success(String message, Object... args) {
        log(LogLevel.SUCCESS, message, args);
    }

    public static void warning(String message, Object... args) {
        log(LogLevel.WARNING, message, args);
    }

    public static void error(String message, Object... args) {
        log(LogLevel.ERROR, message, args);
    }

    public static void separator() {
        System.out.println(CYAN + "═".repeat(80) + RESET);
    }
}