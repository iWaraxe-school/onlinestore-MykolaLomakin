package store.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private HikariDataSource dataSource;

    public ConnectionManager() {
        initializeDataSource();
    }

    private void initializeDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(getDatabaseUrl());
        config.setUsername(getDatabaseUsername());
        config.setPassword(getDatabasePassword());
        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private String getDatabaseUrl() {
        return getProperty("db.url");
    }

    private String getDatabaseUsername() {
        return getProperty("db.username");
    }

    private String getDatabasePassword() {
        return getProperty("db.password");
    }

    private String getProperty(String key) {
        try (InputStream input = new FileInputStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file.", e);
        }
    }
}