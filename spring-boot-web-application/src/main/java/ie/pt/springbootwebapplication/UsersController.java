package ie.pt.springbootwebapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
