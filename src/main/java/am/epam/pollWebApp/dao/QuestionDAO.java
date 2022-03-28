package am.epam.pollWebApp.dao;

import am.epam.pollWebApp.model.Question;

import java.util.List;

public interface QuestionDAO extends PollDAO<Question> {

    List<Question> findByPollId(long pollId);
}
