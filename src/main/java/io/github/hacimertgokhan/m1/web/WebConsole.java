package io.github.hacimertgokhan.m1.web;

import com.google.gson.Gson;
import io.github.hacimertgokhan.m1.core.Database;
import io.github.hacimertgokhan.m1.core.Table;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class WebConsole {

    private final Gson gson = new Gson();

    public void start(Database database, WebConsoleConfig config) {
        port(5807);
        staticFiles.location("/public");

        
        before((req, res) -> {
            String path = req.pathInfo();
            if (!path.equals("/login") && !path.equals("/api/login")) {
                if (req.session(false) == null || req.session().attribute("user") == null) {
                    res.redirect("/login");
                    halt();
                }
            }
        });

        
        get("/login", (req, res) -> new HandlebarsTemplateEngine().render(new ModelAndView(null, "login.hbs")));
        get("/", (req, res) -> new HandlebarsTemplateEngine().render(new ModelAndView(null, "index.hbs")));

        
        post("/api/login", (req, res) -> {
            Map<String, String> payload = gson.fromJson(req.body(), Map.class);
            if (payload.get("username").equals(config.getUsername()) && payload.get("password").equals(config.getPassword())) {
                req.session(true).attribute("user", config.getUsername());
                return gson.toJson(Map.of("status", "success"));
            }
            res.status(401);
            return gson.toJson(Map.of("status", "error", "message", "Invalid username or password."));
        });

        get("/api/tables", (req, res) -> {
            res.type("application/json");
            return gson.toJson(database.getTableNames());
        });

        post("/api/execute", (req, res) -> {
            res.type("application/json");
            String sql = gson.fromJson(req.body(), Map.class).get("sql").toString();
            try {
                if (sql.trim().toLowerCase().startsWith("select")) {
                    Table table = database.executeQuery(sql);
                    return gson.toJson(Map.of("status", "success", "type", "query", "headers", table.getColumnNames(), "rows", table.getRows()));
                } else {
                    int affectedRows = database.executeUpdate(sql);
                    return gson.toJson(Map.of("status", "success", "type", "update", "message", "İşlem başarılı. Etkilenen satır: " + affectedRows));
                }
            } catch (Exception e) {
                res.status(400);
                return gson.toJson(Map.of("status", "error", "message", e.getMessage()));
            }
        });
    }
}