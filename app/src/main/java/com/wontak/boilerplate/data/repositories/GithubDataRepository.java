package com.wontak.boilerplate.data.repositories;

import com.wontak.boilerplate.domain.repositories.GithubRepository;
import com.wontak.boilerplate.network.GithubApiService;
import com.wontak.boilerplate.network.converters.NetworkModelConverter;

import javax.inject.Inject;

import rx.Observable;

public class GithubDataRepository implements GithubRepository
{
    private GithubApiService githubApiService;

    @Inject
    public GithubDataRepository(GithubApiService githubApiService)
    {
        this.githubApiService = githubApiService;
    }

    @Override
    public Observable getUser(String username)
    {
        return githubApiService.getUser(username)
                .map(user -> NetworkModelConverter.convertToDomainModel(user));
    }

    @Override
    public Observable getUserRepositories(String username)
    {
        return githubApiService.getUsersRepositories(username)
                .map(repositories -> NetworkModelConverter.convertToDomainModel(repositories));
    }
}
