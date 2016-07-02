package com.wontak.boilerplate.data.repositories;

import com.wontak.boilerplate.domain.repositories.GithubRepository;
import com.wontak.boilerplate.network.GithubApiService;
import com.wontak.boilerplate.network.converters.NetworkModelConverter;
import com.wontak.boilerplate.network.exceptions.NetworkConnectionException;
import com.wontak.boilerplate.network.exceptions.UserNotFoundException;
import com.wontak.boilerplate.network.models.Repository;
import com.wontak.boilerplate.network.models.User;

import java.util.List;

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

                if (user != null)
                {
                    subscriber.onNext(NetworkModelConverter.convertToDomainModel(user));
                }
                else
                {
                    subscriber.onError(new UserNotFoundException());
                }
            }
            catch (Exception e)
            {
                subscriber.onError(new NetworkConnectionException(e.getCause()));
            }

            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<List<com.wontak.boilerplate.domain.models.Repository>> getUserRepositories(String username)
    {
        return Observable.create(subscriber ->
        {
            try
            {
                Response<List<Repository>> response = githubApiService.getUsersRepositories(username).execute();
                List<Repository> repositories = response.body();

                if (repositories != null)
                {
                    subscriber.onNext(NetworkModelConverter.convertToDomainModel(repositories));
                }
                else
                {
                    subscriber.onError(new NetworkConnectionException());
                }
            }
            catch (Exception e)
            {
                subscriber.onError(new NetworkConnectionException(e.getCause()));
            }

            subscriber.onCompleted();
        });
    }
}
