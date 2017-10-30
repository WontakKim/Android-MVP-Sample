package com.wontak.sample.data.repositories;

import com.wontak.sample.domain.models.Repository;
import com.wontak.sample.domain.models.User;
import com.wontak.sample.domain.repositories.GithubRepository;
import com.wontak.sample.network.GithubApiService;
import com.wontak.sample.network.converters.NetworkModelConverter;

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
