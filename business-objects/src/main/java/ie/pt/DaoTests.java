package ie.pt;

public class DaoTests {

    public static void main(String[] args) {
        
        UserDao dao = new InMemoryUserDao();

        User newUser = new User(-1, "New User", "new.user@gmail.com", true);
        User addedUser = dao.addUser(newUser);

        System.out.println(addedUser);

        User user = dao.getUser(1004);
        System.out.println(user);

        user.setName("Changed");

        dao.updateUser(user);
        dao.deleteUser(1003);
        System.out.println(dao.getUsers());
    }
}
