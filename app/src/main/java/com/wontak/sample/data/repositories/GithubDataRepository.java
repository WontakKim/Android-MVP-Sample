package com.wontak.sample.data.repositories;

import com.wontak.sample.data.converters.RepositoryModelConverter;
import com.wontak.sample.data.converters.UserModelConverter;
import com.wontak.sample.data.network.GithubApiService;
import com.wontak.sample.domain.models.Repository;
import com.wontak.sample.domain.models.User;
import com.wontak.sample.domain.repositories.GithubRepository;

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
                .map(user -> UserModelConverter.convertToDomainModel(user));
    }

    @Override
    public Observable<List<Repository>> getUserRepositories(String username) {
        return githubApiService.getUsersRepositories(username)
                .map(repositories -> RepositoryModelConverter.convertToDomainModel(repositories));
    }
}
