package com.danielrodrigues.app.services;

import com.danielrodrigues.app.entity.User;
import com.danielrodrigues.app.exceptions.RegisteredUserException;
import com.danielrodrigues.app.models.Stats;

public interface IStatsService {

    public Stats getStats(User user);
}
