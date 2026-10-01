package ie.pt.springbootwebapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersRestApiController {

    @Autowired
    UserDao dao;

    @GetMapping
    public List<User> getUsers() {
        return dao.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {

        User user = dao.getUser(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable int id) {

        if (dao.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    ResponseEntity<User> addUser(@RequestBody User user) {

        User addedUser = dao.addUser(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(addedUser);
    }

    @PutMapping("/{id}")
    ResponseEntity<User> updateUser(@RequestBody User user) {

        dao.updateUser(user);
        return ResponseEntity.ok(user);
    }

}
