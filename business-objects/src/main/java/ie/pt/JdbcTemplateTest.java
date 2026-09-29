package ie.pt;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateTest {

    public static void main(String[] args) {


        UserDao dao = new JdbcTemplateUserDao();

        User newUser = new User (-1, "New User", "new.user@gmail.com", true);

        User addedUser = dao.addUser(newUser);

        System.out.println(addedUser);

        dao.getUsers().forEach(System.out::println);

        if (dao.deleteUser(1025)) {
            System.out.println("User deleted");
        } else {
            System.out.println("User not deleted");
        }
        User u = dao.getUser(1000);

        u.setName("CHANGED");
        u.setEmail("changed@gmail.com");
        u.setActive(!u.isActive());

        dao.updateUser(u);

        System.out.println(dao.getUser(1000));
    }
}
