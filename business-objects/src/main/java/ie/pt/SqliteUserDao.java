package ie.pt;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class SqliteUserDao implements UserDao {

    String url = "jdbc:sqlite:C:\\work\\training\\java\\users.db";
    Connection conn;

    public SqliteUserDao() {

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsers() {

        List<User> users = new ArrayList<User>();

        String sql = "SELECT * FROM users";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                boolean active = rs.getBoolean("active");

                User u = new User(id, name, email, active);
                users.add(u);
            }
            rs.close();
            stmt.close();
        }
        catch(SQLException ex) {
            System.out.println("Something went wrong");
        }
        return users;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
