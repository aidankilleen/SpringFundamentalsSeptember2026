package ie.pt;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateTest {

    public static void main(String[] args) {


        UserDao dao = new JdbcTemplateUserDao();

        dao.getUsers().forEach(System.out::println);

    }
}
