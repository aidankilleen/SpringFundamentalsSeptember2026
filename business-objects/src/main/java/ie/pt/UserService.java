package ie.pt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserService {

    @Autowired
    UserDao dao;

    public UserService() {
    }

    // high level user functions here
    List<User> getActiveUsers() {

        List<User> activeUsers = dao.getUsers()
                                    .stream()
                                    .filter(User::isActive)
                                    .toList();
        return activeUsers;
    }

}
