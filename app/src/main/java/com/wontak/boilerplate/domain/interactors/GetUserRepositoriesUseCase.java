package com.wontak.boilerplate.domain.interactors;

import com.wontak.boilerplate.base.BaseUseCase;
import com.wontak.boilerplate.domain.models.Repository;
import com.wontak.boilerplate.domain.repositories.GithubRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GetUserRepositoriesUseCase extends BaseUseCase<List<Repository>, String> {

    private GithubRepository githubRepository;

    @Inject
    public GetUserRepositoriesUseCase(GithubRepository githubRepository) {
        super(Schedulers.io(), AndroidSchedulers.mainThread());

        this.githubRepository = githubRepository;
    }

    @Override
    protected Observable<List<Repository>> buildUseCaseObservable(String username) {
        return githubRepository.getUserRepositories(username);
    }
}
