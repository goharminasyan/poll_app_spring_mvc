package am.epam.pollWebApp.controller;

import am.epam.pollWebApp.dao.AnswerDAO;
import am.epam.pollWebApp.dao.ResultDAO;
import am.epam.pollWebApp.dao.UserDAO;
import am.epam.pollWebApp.model.Result;
import am.epam.pollWebApp.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Controller
@SessionAttributes("user")
public class ResultsController {
    List<Long> list = new ArrayList();
    AnswerDAO answerDAO;
    ResultDAO resultDAO;
    UserDAO userDAO;
    long sum = 0;

    @Autowired
    public ResultsController(AnswerDAO answerDAO, ResultDAO resultDAO, UserDAO userDAO) {
        this.answerDAO = answerDAO;
        this.resultDAO = resultDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("/results")
    public String results(@ModelAttribute("user") Users user, HttpServletRequest req, Model model) throws ParseException {

        String[] questionIds = req.getParameterValues("questionId");
        for (String questionId : questionIds) {
            String parameter = req.getParameter("marked" + questionId);
            if (parameter == null) {
                model.addAttribute("error", "You have not filled in all the fields, try again");
                return "error";
            } else {
                long value = Long.parseLong(parameter);
                sum += value;
            }
        }

        String email = user.getEmail();
        String password = user.getPassword();
        int id = user.getId();
        Result expByScore = resultDAO.findByScore(1, sum);

        Date now = Date.valueOf(LocalDate.now());

        userDAO.getByEmailAndPass(email, password);
        userDAO.updateDate(id, now);

        String explanation = expByScore.getExplanation();
        userDAO.updateResult(id, explanation);
        Users userResult = userDAO.pollResultById(id);

        model.addAttribute("userResult", userResult);
        return "results";

    }
}
