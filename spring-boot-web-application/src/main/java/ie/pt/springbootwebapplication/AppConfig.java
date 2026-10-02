package ie.pt.springbootwebapplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class AppConfig {


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
