package com.wontak.boilerplate.domain.repositories;

import com.wontak.boilerplate.domain.models.Repository;
import com.wontak.boilerplate.domain.models.User;

import java.util.List;

import rx.Observable;

public interface GithubRepository
{
    Observable<User> getUser(String username);
    Observable<List<Repository>> getUserRepositories(String username);
}
