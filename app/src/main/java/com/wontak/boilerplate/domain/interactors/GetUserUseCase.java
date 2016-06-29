package com.wontak.boilerplate.domain.interactors;

import com.wontak.boilerplate.base.BaseUseCase;
import com.wontak.boilerplate.domain.repositories.GithubRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetUserUseCase extends BaseUseCase
{
    private GithubRepository githubRepository;

    private String username;

    @Inject
    public GetUserUseCase(GithubRepository githubRepository)
    {
        super(Schedulers.io(), AndroidSchedulers.mainThread());

        this.githubRepository = githubRepository;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Override
    protected Observable buildUseCaseObservable()
    {
        return githubRepository.getUser(username);
    }
}
