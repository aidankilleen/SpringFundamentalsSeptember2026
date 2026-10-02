package ie.pt.springbootwebapplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Primary
@Transactional
public class JpaUserDao implements UserDao{

    @Autowired
    JpaUserRepository repo;

    @Override
    public List<User> getUsers() {
        return repo.findAll();
    }

    @Override
    public User addUser(User user) {
        return repo.save(user);
    }

    @Override
    public User updateUser(User user) {
        return repo.save(user);
    }

    @Override
    public boolean deleteUser(int id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }

    @Override
    public User getUser(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void close() {

    }
}
