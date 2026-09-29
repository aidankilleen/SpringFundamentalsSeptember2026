package ie.pt;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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

    @Override
    public void close() {

    }
}
