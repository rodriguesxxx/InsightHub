package com.danielrodrigues.app.services;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielrodrigues.app.dto.BCryptDTO;
import com.danielrodrigues.app.entity.User;
import com.danielrodrigues.app.models.Stats;
import com.danielrodrigues.app.utils.BCryptUtil;
import com.danielrodrigues.app.utils.RequestGithubApiUtil;

@Service
public class StatsService implements IStatsService {

    @Autowired
    protected BCryptUtil bCryptUtil;

    @Autowired
    protected RequestGithubApiUtil requestGithubApiUtil;

    public Stats getStats(User user) {
        BCryptDTO bCryptDTO = new BCryptDTO(user.getToken(), user.getKey());
        String token = bCryptUtil.decrypt(bCryptDTO);
        
        requestGithubApiUtil.setToken(token);
        requestGithubApiUtil.setUsername(user.getUsername());
        
        int commitsCount = requestGithubApiUtil.getCommitsCount();
        int issuesCount = requestGithubApiUtil.getIssuesCount();
        int pullRequestCount = requestGithubApiUtil.getPRsCount();

        return new Stats(commitsCount, issuesCount, pullRequestCount);
    }
}
