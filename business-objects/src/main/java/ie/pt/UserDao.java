package ie.pt;

import java.util.List;

public interface UserDao {

    public List<User> getUsers();
    public void close();
}
