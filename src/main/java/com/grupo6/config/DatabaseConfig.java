package com.grupo6.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DatabaseConfig {

    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource(DataSourceProperties properties,
                                 @Value("${DATABASE_URL:}") String databaseUrl) {
        if (databaseUrl != null && !databaseUrl.isBlank()) {
            ParsedDatabaseUrl parsed = parseDatabaseUrl(databaseUrl);
            properties.setUrl(parsed.jdbcUrl);
            if (parsed.username != null) {
                properties.setUsername(parsed.username);
            }
            if (parsed.password != null) {
                properties.setPassword(parsed.password);
            }
        }
        return properties.initializeDataSourceBuilder().build();
    }

    private ParsedDatabaseUrl parseDatabaseUrl(String databaseUrl) {
        try {
            String normalized = databaseUrl.trim();
            if (normalized.startsWith("postgres://")) {
                normalized = "postgresql://" + normalized.substring("postgres://".length());
            }
            URI uri = new URI(normalized);
            String userInfo = uri.getUserInfo();
            String username = null;
            String password = null;
            if (userInfo != null) {
                int idx = userInfo.indexOf(':');
                if (idx >= 0) {
                    username = userInfo.substring(0, idx);
                    password = userInfo.substring(idx + 1);
                } else {
                    username = userInfo;
                }
            }
            String host = uri.getHost();
            int port = uri.getPort();
            String path = uri.getPath();
            if (path == null || path.isBlank()) {
                throw new IllegalArgumentException("DATABASE_URL no contiene el nombre de la base de datos");
            }
            String jdbcUrl = String.format("jdbc:postgresql://%s:%d%s", host, port, path);
            return new ParsedDatabaseUrl(jdbcUrl, username, password);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("DATABASE_URL no es una URI valida: " + databaseUrl, e);
        }
    }

    private static class ParsedDatabaseUrl {
        final String jdbcUrl;
        final String username;
        final String password;

        ParsedDatabaseUrl(String jdbcUrl, String username, String password) {
            this.jdbcUrl = jdbcUrl;
            this.username = username;
            this.password = password;
        }
    }
}
