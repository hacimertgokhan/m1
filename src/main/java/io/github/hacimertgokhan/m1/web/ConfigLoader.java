package io.github.hacimertgokhan.m1.web;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConfigLoader {
    public static WebConsoleConfig loadConfig() throws IOException {
        Yaml yaml = new Yaml();
        try (FileReader reader = new FileReader("options.m1.yaml")) {
            return yaml.loadAs(reader, WebConsoleConfig.class);
        }
    }
}