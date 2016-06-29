package com.wontak.boilerplate.di.modules;

import com.wontak.boilerplate.di.PerActivity;
import com.wontak.boilerplate.domain.interactors.GetUserUseCase;
import com.wontak.boilerplate.domain.repositories.GithubRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule
{
    @Provides
    @PerActivity
    GetUserUseCase provideGetUserUseCase(GithubRepository githubRepository)
    {
        return new GetUserUseCase(githubRepository);
    }
}
