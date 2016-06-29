package com.wontak.boilerplate.data.repositories;

import com.wontak.boilerplate.domain.repositories.GithubRepository;
import com.wontak.boilerplate.network.GithubApiService;
import com.wontak.boilerplate.network.converters.NetworkModelConverter;
import com.wontak.boilerplate.network.models.User;

import javax.inject.Inject;

import retrofit2.Response;
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
    public Observable<com.wontak.boilerplate.domain.models.User> getUser(String username)
    {
        return Observable.create(subscriber ->
        {
            try
            {
                Response<User> response = githubApiService.getUser(username).execute();
                User user = response.body();

                subscriber.onNext(NetworkModelConverter.convertToDomainModel(user));
            }
            catch (Exception e)
            {
                subscriber.onError(e);
            }

            subscriber.onCompleted();
        });
    }
}
