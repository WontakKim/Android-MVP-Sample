package com.wontak.sample.di.modules;

import com.wontak.sample.di.PerActivity;
import com.wontak.sample.domain.interactors.GetUserRepositoriesUseCase;
import com.wontak.sample.domain.interactors.GetUserUseCase;
import com.wontak.sample.domain.repositories.GithubRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    @Provides
    @PerActivity
    GetUserUseCase provideGetUserUseCase(GithubRepository githubRepository) {
        return new GetUserUseCase(githubRepository);
    }

    @Provides
    @PerActivity
    GetUserRepositoriesUseCase provideGetUserRepositoriesUseCase(GithubRepository githubRepository) {
        return new GetUserRepositoriesUseCase(githubRepository);
    }
}
