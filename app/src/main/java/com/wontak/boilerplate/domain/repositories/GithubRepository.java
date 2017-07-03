package com.wontak.boilerplate.domain.repositories;

import io.reactivex.Observable;

public interface GithubRepository {

    Observable getUser(String username);

    Observable getUserRepositories(String username);
}
