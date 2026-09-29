package ie.pt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class JdbcTemplateUserDao implements UserDao {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    RowMapper<User> mapper;

    public JdbcTemplateUserDao() {


    }

    @Override
    public List<User> getUsers() {
        String sql = "SELECT * FROM users";
        return jdbc.query(sql, mapper);
    }

    @Override
    public User addUser(User user) {
        String sql = """
                INSERT INTO users
                (name, email, active)
                VALUES(?, ?, ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    sql,
                    new String[]{"id"}
            );
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setBoolean(3, user.isActive());

            return ps;
        }, keyHolder);

        int newId = keyHolder.getKey().intValue();

        return new User (newId,
                        user.getName(),
                        user.getEmail(),
                        user.isActive());
    }

    @Override
    public User updateUser(User user) {
        String sql = """
                    UPDATE users 
                        SET name=?, email=?, active=? 
                    WHERE id = ?
                    """;
        return jdbc.update(sql,
                        user.getName(),
                        user.getEmail(),
                        user.isActive(),
                        user.getId()) > 0 ? user : null;
    }

    @Override
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        return jdbc.update(sql, id) > 0;
    }

    @Override
    public User getUser(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> users = jdbc.query(sql, mapper, id);
        return users.isEmpty() ? null : users.getFirst();
    }

    @Override
    public void close() {

    }
}
