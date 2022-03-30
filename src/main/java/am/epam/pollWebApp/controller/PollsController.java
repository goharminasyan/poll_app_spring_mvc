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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("user")
public class PollsController {
    PollDAO pollDAO;
    UserDAO userDAO;

    int today = 0;
    int day = 7;
    Date date = new Date(0000, 00, 00);

    @Autowired
    public PollsController(PollDAO pollDAO, UserDAO userDAO) {
        this.pollDAO = pollDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("/poll")
    public String openPollPage(@ModelAttribute("user") Users user, Model model) {

        int id = user.getId();
        Date result_date = user.getResult_date();

        if (result_date == null || result_date.toLocalDate().getDayOfMonth() + 7 < today) {
            userDAO.updateDate(id, date);
            List all = pollDAO.findAll();
            model.addAttribute("polls", all);
            return "polls";
        }else  {
            int resultDate = result_date.toLocalDate().getDayOfMonth();
            today = LocalDate.now().getDayOfMonth();
            day += resultDate;
            if (day >= today) {
                Users byId = userDAO.pollResultById(id);
                model.addAttribute("poll_error", "Вы можете снова принять участие в опросе через неделю \n Ваш результат -");
                model.addAttribute("poll_result", byId);
                return "results";
            }
        }
        return null;
    }
}

