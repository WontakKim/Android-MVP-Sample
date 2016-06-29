package com.wontak.boilerplate.domain.repositories;

import com.wontak.boilerplate.domain.models.User;

import rx.Observable;

public interface GithubRepository
{
    Observable<User> getUser(String username);
}
