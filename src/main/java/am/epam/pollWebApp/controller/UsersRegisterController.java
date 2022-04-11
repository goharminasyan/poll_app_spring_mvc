package am.epam.pollWebApp.controller;

import am.epam.pollWebApp.dao.UserDAO;
import am.epam.pollWebApp.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(UsersRegisterController.class);

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
            logger.error("The user has not passed the registration validation, something went wrong");
            return "register";
        } else {
            userDAO.create(user);
            logger.info("User was created");
            model.addAttribute("registerMessage", "You have successfully registered!");
            return "login";
        }
    }
}
