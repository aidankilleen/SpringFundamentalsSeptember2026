package ie.pt.springbootwebapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UsersController {

    @Autowired
    UserDao dao;

    @GetMapping("/users")
    String users(Model model) {

        model.addAttribute("title", "Users Page");

        model.addAttribute(
                "users",
                dao.getUsers());

        return "users";
    }

    @GetMapping("/users/{id}")
    String userdetail(@PathVariable int id, Model model) {

        User user = dao.getUser(id);
        model.addAttribute("user", user);
        return "userdetail";
    }

    @GetMapping("/users/delete/{id}")
    String deleteUser(@PathVariable int id) {

        // Confirm Delete before doing the actual
        // delete!!!!!
        // TODO - delete the user
        // dao.deleteUser(id);

        // show a message
        return "redirect:/users";
    }
}
