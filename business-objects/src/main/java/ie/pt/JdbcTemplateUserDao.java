package ie.pt;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;

public class JdbcTemplateUserDao implements UserDao {

    JdbcTemplate jdbc;
    RowMapper<User> mapper;

    public JdbcTemplateUserDao() {

        // initialise the jdbc
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.sqlite.JDBC");
        ds.setUrl("jdbc:sqlite:C:\\work\\training\\java\\users.db");

        jdbc = new JdbcTemplate(ds);

        // initialise the mapper
        mapper = (rs, rowNum)-> {
            return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getBoolean("active")
            );
        };
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
