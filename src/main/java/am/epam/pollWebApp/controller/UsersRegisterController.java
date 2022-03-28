package am.epam.pollWebApp.controller;

import am.epam.pollWebApp.dao.UserDAOImpl;
import am.epam.pollWebApp.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersRegisterController {
    private UserDAOImpl userDAO;

    @Autowired
    public UsersRegisterController(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/register")
    public String openRegisterPage(@ModelAttribute("user") Users user) {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") Users user, Model model ) {
        userDAO.create(user);
        model.addAttribute("registerMessage", "You have successfully registered!");
        return "/login";
    }

}
