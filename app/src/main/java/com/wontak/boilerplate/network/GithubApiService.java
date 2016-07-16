package com.wontak.boilerplate.network;

import com.wontak.boilerplate.network.models.Repository;
import com.wontak.boilerplate.network.models.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GithubApiService
{
    @GET("/users/{username}")
    Observable<User> getUser(
            @Path("username") String username
    );

    @GET("/users/{username}/repos")
    Observable<List<Repository>> getUsersRepositories(
            @Path("username") String username
    );
}
