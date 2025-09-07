package io.github.hacimertgokhan.m1.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class M1Driver implements Driver {
    private static final String URL_PREFIX = "jdbc:m1:";

    
    
    static {
        try {
            DriverManager.registerDriver(new M1Driver());
        } catch (SQLException e) {
            throw new RuntimeException("M1Driver cannot saved: " + e);
        }
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        if (!acceptsURL(url)) {
            return null; 
        }
        return new M1Connection(url);
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return url != null && url.startsWith(URL_PREFIX);
    }

    @Override public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) { return new DriverPropertyInfo[0]; }
    @Override public int getMajorVersion() { return 1; }
    @Override public int getMinorVersion() { return 0; }
    @Override public boolean jdbcCompliant() { return false; }
    @Override public Logger getParentLogger() throws SQLFeatureNotSupportedException { throw new SQLFeatureNotSupportedException(); }
}