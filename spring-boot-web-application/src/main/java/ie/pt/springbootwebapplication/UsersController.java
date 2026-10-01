package ie.pt.springbootwebapplication;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    String deleteUser(@PathVariable int id, Model model) {

        model.addAttribute("id", id);

        // Show the Confirm Delete before doing the actual
        // delete!!!!!
        return "confirm";
    }

    @PostMapping("/users/delete/{id}")
    String doDeleteUser(@RequestParam("id") int id) {

        System.out.println("delete user " + id);
        // actually do the delete here
        dao.deleteUser(id);

        // show a message
        return "redirect:/users";
    }

    @GetMapping("/users/add")
    String addUserForm(Model model) {

        User newUser = new User();
        model.addAttribute("user", newUser);
        model.addAttribute("title", "Add User");
        model.addAttribute("action", "/users/add");
        return "userform";
    }
    @PostMapping("/users/add")
    String doAddUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            return "userform";
        }
        User addedUser = dao.addUser(user);
        System.out.println(addedUser);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    String editUserForm(@PathVariable int id, Model model) {

        User user = dao.getUser(id);
        model.addAttribute("title", "Edit User");
        model.addAttribute("action", "/users/edit");
        model.addAttribute("user", user);
        return "userform";
    }

    @PostMapping("/users/edit")
    String doEditUser(@ModelAttribute("user") User user) {
        dao.updateUser(user);
        return "redirect:/users";
    }
}
