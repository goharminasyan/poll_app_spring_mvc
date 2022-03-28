package am.epam.pollWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {

    @PostMapping("/logout")
    public String logout (Model model){
        model.addAttribute("logout", "You are successfully logged out!" );
        return "login";
    }
}
