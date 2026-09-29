package ie.pt;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class InMemoryUserDao implements UserDao {

    List<User> users = new ArrayList<User>();

    public InMemoryUserDao() {

        users.add(new User(1000, "Zoe", "zoe@gmail.com", false));
        users.add(new User(1001, "Yvonne", "yvonne@gmail.com", true));
        users.add(new User(1002, "Xavier", "xavier@gmail.com", true));
        users.add(new User(1003, "Wendy", "wendy@gmail.com", false));
        users.add(new User(1004, "Victor", "victor@gmail.com", true));
        users.add(new User(1005, "Ultan", "ultan@gmail.com", true));
        users.add(new User(1006, "Tamara", "tamara@gmail.com", false));
        users.add(new User(1007, "Sophie", "sophie@gmail.com", true));
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public User addUser(User user) {

        int nextId = users.stream()
                .mapToInt(User::getId)
                .max()
                .orElse(0) + 1;

        user.setId(nextId);
        users.add(user);
        return user;
    }

    @Override
    public User updateUser(User user) {

        for (int i=0; i<users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
            }
        }
        return user;
    }

    @Override
    public boolean deleteUser(int id) {
        return users.removeIf(user -> user.getId() == id);
    }

    @Override
    public User getUser(int id) {
        return users.stream()
                .filter(user->user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void close() {
        users.clear();
    }
}
