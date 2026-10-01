package ie.pt.springbootwebapplication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientSideUsersController {

    @GetMapping("/clientsideusers")
    String getClientSideUsers() {

        return "clientsideusers";
    }
}
