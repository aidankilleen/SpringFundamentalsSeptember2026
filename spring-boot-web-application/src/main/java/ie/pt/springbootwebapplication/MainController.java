package ie.pt.springbootwebapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

// @Controller is a stereotype like @Component
// so spring will instantiate this object
// automatically

// controllers handle web requests

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);


    @GetMapping("/")
    public String home(Model model) {
        log.info("/home requested");

        List<String> names = new ArrayList<>();
        names.add("alice");
        names.add("bob");
        names.add("carol");
        names.add("dan");

        model.addAttribute("title",
                "Home Page");

        model.addAttribute("names", names);

        User u = new User(1, "Alice", "alice@gmail.com", true);
        model.addAttribute("user", u);
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }


}
