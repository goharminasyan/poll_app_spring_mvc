package am.epam.pollWebApp.dao;

import am.epam.pollWebApp.model.Result;

import java.util.List;

public interface ResultDAO extends PollDAO<Result> {

    List<Result> findByPollId(long pollId);

    Result findByScore(long pollId, long score);

}
