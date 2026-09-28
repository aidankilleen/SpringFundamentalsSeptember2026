package ie.pt;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryUserDao implements UserDao {

    List<User> users = new ArrayList<User>();

    public InMemoryUserDao() {

        users.add(new User(1000, "Zoe", "zoe@gmail.com", false));
        users.add(new User(1001, "Yvonne", "yvonne@gmail.com", true));
        users.add(new User(1002, "Xavier", "xavier@gmail.com", true));
        users.add(new User(1003, "Wendy", "wendy@gmail.com", false));
    }

    public List<User> getUsers() {
        return users;
    }

    public void close() {
        users.clear();
    }
}
