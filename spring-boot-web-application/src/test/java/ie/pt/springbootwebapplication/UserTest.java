package ie.pt.springbootwebapplication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// JUnit uses annotated POJOs rather than
// the old version where every test class inherited from a base class

public class UserTest {

    @Test
    void canCreateUser() {
        User user = new User(1, "Alice", "alice@gmail.com", false);
        assertNotNull(user);
    }

    @Test
    void canCompareUsers() {
        User u1 = new User(1, "Alice", "alice@gmail.com", false);
        User u2 = new User(1, "Alice", "alice@gmail.com", false);
        assertEquals(u1, u2);
    }
}
