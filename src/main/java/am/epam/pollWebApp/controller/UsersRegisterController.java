package am.epam.pollWebApp.controller;

import am.epam.pollWebApp.dao.UserDAO;
import am.epam.pollWebApp.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UsersRegisterController {
    private UserDAO userDAO;

    @Autowired
    public UsersRegisterController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/register")
    public String openRegisterPage(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid Users user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        } else {
            userDAO.create(user);
            model.addAttribute("registerMessage", "You have successfully registered!");
            return "login";
        }
    }
}
