package ie.pt.springboothelloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Value("${database.url}")
    private String databaseUrl;

    @Bean
    String message() {
        return "is spring working?";
    }

    @Bean
    User getUser() {
        return new User(1, "Alice", "alice@gmail.com", false);
    }

    @Bean
    Connection connection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(databaseUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    @Bean
    RowMapper<User> mapper() {
        // initialise the mapper
        return (rs, rowNum) -> {
            return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getBoolean("active")
            );
        };
    }

}
