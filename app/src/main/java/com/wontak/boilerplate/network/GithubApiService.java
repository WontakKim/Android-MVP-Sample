package com.wontak.boilerplate.network;

import com.wontak.boilerplate.network.models.Repository;
import com.wontak.boilerplate.network.models.User;

import java.util.List;

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
    Call<List<Repository>> getUsersRepositories(
            @Path("username") String username
    );
}
