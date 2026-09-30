package ie.pt.springboothelloworld;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SqliteUserDao implements UserDao {

    @Value("${database.url}")
    String url; // = "jdbc:sqlite:C:\\work\\training\\java\\users.db";

    @Autowired
    Connection conn;

    public SqliteUserDao() {

        // NB:
        // we can't use url in the constructor
        // spring is going to instantiate the bean
        // only then can it do the injection of
        // objects and properties

        // it is a very common error to forget this!
        /*try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    // using @PostConstruct
    // identifies a method to be called when spring has the
    // object ready (all injections are complete)

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct called");

        // remember if we can we should get Spring
        // to instantiate our objects  for us.
        /*
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        */
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
    public User addUser(User userToAdd) {

        User addedUser = null;

        /*
        String sql = String.format("""
                INSERT INTO users
                (Name, Email, Active)
                VALUES('%s', '%s', %d)
                """, userToAdd.getName(),
                userToAdd.getEmail(),
                userToAdd.isActive() ? 1 : 0);
        */
        // a prepared statement gets the db engine to the substitutions
        // use ?'s as place holders
        String sql = """
                INSERT INTO users
                (Name, Email, Active)
                VALUES(?, ?, ?)""";
        // System.out.println(sql);

        try {
            //Statement stmt = conn.createStatement();
            PreparedStatement stmt = conn.prepareStatement(sql);
            // replace the ?s with values
            // NB: the database engine is doing this
            // we are not putting values directly into the sql
            stmt.setString(1, userToAdd.getName());
            stmt.setString(2, userToAdd.getEmail());
            stmt.setBoolean(3, userToAdd.isActive());

            stmt.executeUpdate();

            // Get the newly created id
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int newId = rs.getInt(1);
                addedUser = new User(newId,
                        userToAdd.getName(),
                        userToAdd.getEmail(),
                        userToAdd.isActive());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return addedUser;
    }

    @Override
    public User updateUser(User u) {

        /*
        String sql = "UPDATE users " +
                "SET " +
                "name = '" + u.getName() + "', " +
                "email = '"+ u.getEmail() + "'," +
                "active = " + (u.isActive() ? 1 : 0) +
                " WHERE id = " + u.getId();
        */

        String sql = """
                        UPDATE users 
                        SET name = ?,
                        email = ?,
                        active = ?
                        WHERE id = ?""";

        //System.out.println(sql);
        try {
            //Statement stmt = conn.createStatement();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getEmail());
            stmt.setBoolean(3, u.isActive());
            stmt.setInt(4, u.getId());
            int n = stmt.executeUpdate();
            if (n == 0) {
                // no records updated
                // TODO - is this an error
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return u;
    }

    @Override
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int n = stmt.executeUpdate();

            if (n == 0) {
                // no records deleted
                // TODO - is this an error?
            }
            return n != 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUser(int id) {
        User user = null;

        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                boolean active = rs.getBoolean("active");

                user = new User(id, name, email, active);
            }
            rs.close();
            stmt.close();
        }
        catch(SQLException ex) {
            System.out.println("Something went wrong");
        }
        return user;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
