package ie.pt;

public class DaoTests {

    public static void main(String[] args) {
        
        //UserDao dao = new InMemoryUserDao();
        UserDao dao = new SqliteUserDao();

        User user = dao.getUser(5);
        System.out.println(user);

        if (dao.deleteUser(0)) {
            System.out.println("User deleted");
        } else {
            System.out.println("No user deleted");
        }


        User newUser = new User(-1, "New User", "new.user@gmail.com", true);
        User addedUser = dao.addUser(newUser);

        System.out.println(addedUser);

/*

        user.setName("Changed");

        dao.updateUser(user);
        dao.deleteUser(1003);

         */
        System.out.println(dao.getUsers());
    }
}
