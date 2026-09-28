package ie.pt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("ie.pt")
public class AppConfig {

    @Bean
    String getString() {
        return "This is a string from AppConfig";
    }

    @Bean
    @Primary
    Message welcomeMessage() {
        Message m = new Message("Annotation Driven Config", "Annotation driven config rocks!");
        return m;
    }

    @Bean
    Message exitMessage() {
        Message m = new Message("Goodbye Message", "thanks for using the system");
        return m;
    }





}
