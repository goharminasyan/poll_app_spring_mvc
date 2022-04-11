package am.epam.pollWebApp.controller;

import am.epam.pollWebApp.dao.AnswerDAO;
import am.epam.pollWebApp.dao.QuestionDAO;
import am.epam.pollWebApp.dao.UserDAO;
import am.epam.pollWebApp.model.Answer;
import am.epam.pollWebApp.model.Question;
import am.epam.pollWebApp.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@SessionAttributes("user")
public class QuestionsAnswersController {
    QuestionDAO questionDAO;
    AnswerDAO answerDAO;
    UserDAO userDAO;
    Question question = new Question();
    Answer answer = new Answer();
    List<Answer> answers = new ArrayList<>();

    private Logger logger = LoggerFactory.getLogger(QuestionsAnswersController.class);

    @Autowired
    public QuestionsAnswersController(QuestionDAO questionDAO, AnswerDAO answerDAO, UserDAO userDAO) {
        this.questionDAO = questionDAO;
        this.answerDAO = answerDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("/questions/{pollId}")
    public String questionsAndAnswers(@ModelAttribute("user") Users user, @PathVariable("pollId") int poll_id, Model model) {

        Date result_date = user.getResult_date();
        Date now = Date.valueOf(LocalDate.now());

        if (result_date == null) {
            question.setPollId(poll_id);
            List<Question> questions = questionDAO.findByPollId(question.getPollId());
            return getQuestions(model, questions, answer, answers);
        } else {
            long diffInDays = Math.abs(now.getTime() - result_date.getTime());
            long diff = TimeUnit.DAYS.convert(diffInDays, TimeUnit.MILLISECONDS);

            if (diff <= 7) {
                Users byId = userDAO.pollResultById(user.getId());
                model.addAttribute("poll_error", "Вы можете снова принять участие в опросе через неделю \n Ваш результат -");
                logger.error("The user participated in the survey during the last 7 days");
                model.addAttribute("poll_result", byId);
                return "results";
            } else {
                question.setPollId(poll_id);
                List<Question> questions = questionDAO.findByPollId(question.getPollId());
                return getQuestions(model, questions, answer, answers);
            }
        }
    }

    private String getQuestions(Model model, List<Question> questions, Answer answer2, List<Answer> answers2) {
        for (Question quest : questions) {
            long questId = quest.getId();
            answer2.setQuestion_id(questId);
            answers2.addAll(answerDAO.findByQuestionId(answer2.getQuestion_id()));
        }
        model.addAttribute("questions", questions);
        model.addAttribute("answers", answers2);
        logger.info("The user's preferred poll was successfully opened");
        return "question_answers";
    }
}

