package am.epam.pollWebApp.dao;


import am.epam.pollWebApp.model.Answer;

import java.util.List;

public interface AnswerDAO extends PollDAO<Answer> {

    List<Answer> findByQuestionId(long questionId);
}
