package ie.pt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ie.pt")
public class AppConfig {

    /*
    @Bean
    UserDao getDao() {

        return new InMemoryUserDao();
    }

     */
}
