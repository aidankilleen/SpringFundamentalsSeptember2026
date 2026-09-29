package ie.pt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.parsing.SourceExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("ie.pt")
public class AppConfig {

    @Value("${database.url}")
    private String databaseUrl;


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
    DriverManagerDataSource ds() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.sqlite.JDBC");
        ds.setUrl(databaseUrl);
        return ds;
    }

    @Bean
    JdbcTemplate jdbc() {
        return new JdbcTemplate(ds());
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
    /*
    @Bean
    UserDao getDao() {

        return new InMemoryUserDao();
    }

     */
}
