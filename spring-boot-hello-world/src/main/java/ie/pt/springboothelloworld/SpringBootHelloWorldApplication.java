package ie.pt.springboothelloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootHelloWorldApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SpringBootHelloWorldApplication.class);

    @Value("${spring.application.name}")
    String title;

    @Autowired
    ApplicationContext context;

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHelloWorldApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("Application {} has started", title);

        System.out.println(title);

        /*
        String message = context.getBean(String.class);

        System.out.println(message);
        User u = context.getBean(User.class);
        System.out.println(u);

        UserDao dao = context.getBean(UserDao.class);
        dao.getUsers().forEach(System.out::println);
         */
        System.out.println("---------------------------");
        //UserService svc = context.getBean(UserService.class);
        userService.getActiveUsers().forEach(System.out::println);
    }
}
