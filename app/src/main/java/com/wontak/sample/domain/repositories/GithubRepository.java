package com.wontak.sample.domain.repositories;

import com.wontak.sample.domain.models.Repository;
import com.wontak.sample.domain.models.User;

import java.util.List;

import io.reactivex.Observable;

public interface GithubRepository {

    Observable<User> getUser(String username);

    Observable<List<Repository>> getUserRepositories(String username);
}
