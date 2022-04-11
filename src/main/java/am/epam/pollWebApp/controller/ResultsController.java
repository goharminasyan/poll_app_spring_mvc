package am.epam.pollWebApp.controller;

import am.epam.pollWebApp.dao.AnswerDAO;
import am.epam.pollWebApp.dao.ResultDAO;
import am.epam.pollWebApp.dao.UserDAO;
import am.epam.pollWebApp.model.Result;
import am.epam.pollWebApp.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;

@Controller
@SessionAttributes("user")
public class ResultsController {
    AnswerDAO answerDAO;
    ResultDAO resultDAO;
    UserDAO userDAO;

    private Logger logger = LoggerFactory.getLogger(ResultsController.class);

    @Autowired
    public ResultsController(AnswerDAO answerDAO, ResultDAO resultDAO, UserDAO userDAO) {
        this.answerDAO = answerDAO;
        this.resultDAO = resultDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("/results")
    public String results(@ModelAttribute("user") Users user, HttpServletRequest req, Model model) {

        String[] questionIds = req.getParameterValues("questionId");
        long sum = 0;
        for (String questionId : questionIds) {
            String parameter = req.getParameter("marked" + questionId);
            if (parameter == null) {
                model.addAttribute("error", "You have not filled in all the fields, try again");
                logger.error("The user have not filled in all the fields");
                return "error";
            } else {
                long value = Long.parseLong(parameter);
                sum += value;
            }
        }

        int id = user.getId();
        Result expByScore = resultDAO.findByScore(1, sum);
        String explanation = expByScore.getExplanation();

        Date now = Date.valueOf(LocalDate.now());

        userDAO.updateDate(id, now);
        userDAO.updateResult(id, explanation);

        Users userResult = userDAO.pollResultById(id);

        model.addAttribute("userResult", userResult);
        logger.info("Everything was done successfully, user's data was added to the db and displayed to the user");
        return "results";
    }
}
