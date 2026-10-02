package ie.pt.springbootwebapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class SqliteUserDaoTest extends UserDaoTest {

    @Autowired
    SqliteUserDao dao;

    @Override
    protected UserDao dao() {
        return dao;
    }

    @Override
    protected void configureTest() {
        System.out.println("****SqliteUserDao specific setup");
    }
}
