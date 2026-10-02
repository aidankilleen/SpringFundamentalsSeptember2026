package ie.pt.springbootwebapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class JPAInvestigationController {

    @Autowired
    JpaUserRepository repo;

    @GetMapping("/jpa")
    public String jpaInvestigation(Model model) {

        // TODO jpa investigaton goes here

        List<User> users = repo.findAll();

        users.forEach(System.out::println);

        model.addAttribute("users", users);
        model.addAttribute("title", "JPA Users");
        return "users";
    }
}
