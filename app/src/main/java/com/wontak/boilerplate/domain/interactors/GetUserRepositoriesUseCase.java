package com.wontak.boilerplate.domain.interactors;

import com.wontak.boilerplate.base.BaseUseCase;
import com.wontak.boilerplate.domain.repositories.GithubRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetUserRepositoriesUseCase extends BaseUseCase<String>
{
    private GithubRepository githubRepository;

    @Inject
    public GetUserRepositoriesUseCase(GithubRepository githubRepository)
    {
        super(Schedulers.io(), AndroidSchedulers.mainThread());

        this.githubRepository = githubRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(String username)
    {
        return githubRepository.getUserRepositories(username);
    }
}
