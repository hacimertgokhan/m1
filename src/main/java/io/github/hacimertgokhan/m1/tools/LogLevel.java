package io.github.hacimertgokhan.m1.tools;

import static io.github.hacimertgokhan.m1.tools.M1Logger.*;

public enum LogLevel {
    DEBUG(CYAN, "🐛"),
    INFO(BLUE, "ℹ️"),
    SUCCESS(GREEN, "✅"),
    WARNING(YELLOW, "⚠️"),
    ERROR(RED, "❌");

    final String color;
    final String icon;

    LogLevel(String color, String icon) {
        this.color = color;
        this.icon = icon;
    }
}