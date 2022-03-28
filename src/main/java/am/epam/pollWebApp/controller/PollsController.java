package am.epam.pollWebApp.controller;

import am.epam.pollWebApp.dao.PollDAO;
import am.epam.pollWebApp.dao.PollDAOImpl;
import am.epam.pollWebApp.dao.UserDAO;
import am.epam.pollWebApp.dao.UserDAOImpl;
import am.epam.pollWebApp.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
public class PollsController {
    PollDAO pollDAO;
    UserDAO userDAO;

    @Autowired
    public PollsController(PollDAO pollDAO, UserDAO userDAO) {
        this.pollDAO = pollDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("/poll")
    public String openPollPage(Model model) {
            List all = pollDAO.findAll();
            model.addAttribute("polls", all);
        return "polls";
    }




}
