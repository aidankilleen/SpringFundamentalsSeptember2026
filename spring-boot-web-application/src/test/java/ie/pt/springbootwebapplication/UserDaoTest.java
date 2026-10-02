package ie.pt.springbootwebapplication;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public abstract class UserDaoTest {

    protected abstract UserDao dao();

    protected abstract void configureTest();

    @BeforeAll
    static void setupTestRun() {
        System.out.println("*****Starting Tests");
    }

    @AfterAll
    static void teardownAll() {
        System.out.println("*****Finished Tests");
    }

    @BeforeEach
    void setup() {
        System.out.println("setup()");
        User addedUser = new User(-1,"John", "john@gmail.com", false);
        dao().addUser(addedUser);
        configureTest();

    }

    @AfterEach
    void teardown() {
        System.out.println("teardown()");
        List<User> users = dao().getUsers();
        List<User> usersToDelete = dao().getUsers()
                .stream()
                .filter(user -> {
                    if (user.getName().equals("John")) {
                        return true;
                    } else {
                        return false;
                    }
                }).toList();
        usersToDelete.forEach(user->dao().deleteUser(user.id));
    }

    @Test
    void canOpenDatabase() {
        List<User> users = dao().getUsers();
        assertNotEquals(0, users.size());
    }

    @Test
    void cantFindUser() {
        User user = dao().getUser(9999);
        assertNull(user);
    }

    @Test
    void addUserIdIsIgnored() {
        User user = new User(1000, "Zoe", "zoe@gmail.com", false);
        User addedUser = dao().addUser(user);
        assertNotEquals(1000, addedUser.getId());
    }

}
