package com.wontak.boilerplate.data.repositories;

import com.wontak.boilerplate.domain.models.Repository;
import com.wontak.boilerplate.domain.models.User;
import com.wontak.boilerplate.domain.repositories.GithubRepository;
import com.wontak.boilerplate.network.GithubApiService;
import com.wontak.boilerplate.network.converters.NetworkModelConverter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GithubDataRepository implements GithubRepository {

    private GithubApiService githubApiService;

    @Inject
    public GithubDataRepository(GithubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }

    @Override
    public Observable<User> getUser(String username) {
        return githubApiService.getUser(username)
                .map(user -> NetworkModelConverter.convertToDomainModel(user));
    }

    @Override
    public Observable<List<Repository>> getUserRepositories(String username) {
        return githubApiService.getUsersRepositories(username)
                .map(repositories -> NetworkModelConverter.convertToDomainModel(repositories));
    }
}
