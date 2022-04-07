package am.epam.pollWebApp.controller;

import am.epam.pollWebApp.dao.PollDAO;
import am.epam.pollWebApp.dao.UserDAO;
import am.epam.pollWebApp.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@SessionAttributes("user")
public class PollsController {
    PollDAO pollDAO;
    UserDAO userDAO;

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
        Date now = Date.valueOf(LocalDate.now());

        if (result_date != null) {
            long diffInDays = Math.abs(now.toLocalDate().getDayOfMonth() - result_date.toLocalDate().getDayOfMonth());
            long diff = TimeUnit.DAYS.convert(diffInDays, TimeUnit.DAYS);

            if (diff > 7) {
                userDAO.updateDate(id, date);
                List all = pollDAO.findAll();
                model.addAttribute("polls", all);
                return "polls";
            } else if (diff <= 7) {
                Users byId = userDAO.pollResultById(id);
                model.addAttribute("poll_error", "Вы можете снова принять участие в опросе через неделю \n Ваш результат -");
                model.addAttribute("poll_result", byId);
                return "results";
            }
        } else{
            List all = pollDAO.findAll();
            model.addAttribute("polls", all);
            return "polls";
        }
        return null;
    }
}

