package ie.pt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {


        // enterprise coding concepts
        // there may be more than one object that provides a certain set of functions
        // each object should implement the same set of functions
        // in Java this is done by "coding to an interface"

        // Spring allows us to code to interfaces rather than implementations

        //UserDao dao = new InMemoryUserDao();
        //UserDao dao = new SqliteUserDao();

        // enterprise software development concepts
        // #1 - don't instantiate your own objects get them from the (spring) framework
        //  (inversion of control / dependency injection)
        // #2 - there might be more than one object providing a set of features
        //  (coding to interfaces rather than implementations)
        // #3 - swapping in and out implementations should be simple
        //  (loose coupling)
        // #4 - annotation driven development is very common

        ApplicationContext ctx
                = new AnnotationConfigApplicationContext(AppConfig.class);

        /*
        UserDao dao = ctx.getBean(UserDao.class);

        dao.getUsers().forEach(System.out::println);
        */
        // enterprise sw don't instantiate objects
        // get them from the framework
        // UserService svc = new UserService(dao);

        String title = ctx.getEnvironment().getProperty("app.title");

        System.out.println(title);


        UserService svc = ctx.getBean(UserService.class);




        List<User> users = svc.getActiveUsers();

        for (User u : users) {
            System.out.println(u);
        }


        // this shows clearly WHY having spring create the objects
        // is a good idea and the popularity of the "Inversion of Control"
        // and dependency injection

        // UserService
        // UserDao
        //  JdbcTemplateUserDao
        // JdbcTemplate
        // DriverManagerDataSource
        // connection string







        //dao.close();
        /*
        String url = "jdbc:sqlite:C:\\work\\training\\java\\users.db";

        Connection conn = DriverManager.getConnection(url);
        String sql = "SELECT * FROM users";
        PreparedStatement stmt = conn.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        List<User> users = new ArrayList<User>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            boolean active = rs.getBoolean("active");

            User u = new User(id, name, email, active);
            users.add(u);
            System.out.println(name);
        }
        rs.close();
        stmt.close();
        conn.close();

        for (User u: users) {
            System.out.println(u);
        }
        */

    }
}
