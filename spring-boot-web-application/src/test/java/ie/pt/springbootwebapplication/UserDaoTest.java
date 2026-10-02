package ie.pt.springbootwebapplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class UserDaoTest {

    @Autowired
    UserDao dao;

    @Test
    void canOpenDatabase() {
        List<User> users = dao.getUsers();
        assertEquals("there are 8 records", 8, users.size());
    }

    @Test
    void cantFindUser() {
        User user = dao.getUser(9999);
        assertNull(user);
    }

    @Test
    void addUserIdIsIgnored() {
        User user = new User(1000, "Zoe", "zoe@gmail.com", false);
        User addedUser = dao.addUser(user);
        assertNotEquals(user.getId(), addedUser.getId());
    }
    
}
