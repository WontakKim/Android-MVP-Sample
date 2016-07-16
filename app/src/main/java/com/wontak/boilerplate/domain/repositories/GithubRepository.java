package com.wontak.boilerplate.domain.repositories;

import rx.Observable;

public interface GithubRepository
{
    Observable getUser(String username);
    Observable getUserRepositories(String username);
}
