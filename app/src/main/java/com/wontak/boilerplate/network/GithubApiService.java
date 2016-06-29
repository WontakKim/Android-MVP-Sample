package com.wontak.boilerplate.network;

import com.wontak.boilerplate.network.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApiService
{
    @GET("/users/{username}")
    Call<User> getUser(
            @Path("username") String username
    );

    @GET("/users/{username}/repos")
    Call getUsersRepositories(
            @Path("username") String username
    );
}
