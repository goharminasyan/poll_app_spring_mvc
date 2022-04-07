package am.epam.pollWebApp.controller;

import am.epam.pollWebApp.dao.UserDAO;
import am.epam.pollWebApp.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("user")
public class UsersLoginController {

    private UserDAO userDAO;

    @Autowired
    public UsersLoginController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/login")
    public String openLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String validDate(@RequestParam(value = "email", required = false) String email,
                            @RequestParam(value = "password", required = false) String password,
                            Model model) {

        if (!userDAO.existEmailAndPass(email, password)) {
            model.addAttribute("errorMassage", "Something wrong");
            return "login";
        } else {
            Users user = (Users) userDAO.getByEmailAndPass(email, password);
            model.addAttribute("user", user);
            return "home";
        }
    }
}

