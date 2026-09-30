package ie.pt.springboothelloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserDao dao;

    public UserService() {
    }

    // high level user functions here
    List<User> getActiveUsers() {

        log.info("Getting active users");
        log.error("Something went wrong");
        log.warn("Warning...");
        log.debug("Debug message");

        List<User> activeUsers = dao.getUsers()
                                    .stream()
                                    .filter(User::isActive)
                                    .toList();
        return activeUsers;
    }

}
