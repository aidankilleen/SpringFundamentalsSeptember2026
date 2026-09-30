package ie.pt.springboothelloworld;

import java.util.List;

public interface UserDao {

    public List<User> getUsers();
    public User addUser(User user);
    public User updateUser(User user);
    public boolean deleteUser(int id);
    public User getUser(int id);
    public void close();
}
