package am.epam.pollWebApp.controller;

import am.epam.pollWebApp.dao.AnswerDAO;
import am.epam.pollWebApp.dao.ResultDAO;
import am.epam.pollWebApp.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ResultsController {
    List<Long> list = new ArrayList();
    AnswerDAO answerDAO;
    ResultDAO resultDAO;
    long sum = 0;

    @Autowired
    public ResultsController(AnswerDAO answerDAO, ResultDAO resultDAO) {
        this.answerDAO = answerDAO;
        this.resultDAO = resultDAO;
    }

    @GetMapping("/results")
    public String results(HttpServletRequest req, Model model) {

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
        Result byScore = resultDAO.findByScore(1, sum);
       model.addAttribute("weight", byScore);
        return "results";
    }
}
