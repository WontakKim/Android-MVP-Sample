package com.wontak.boilerplate.network.converters;

import com.wontak.boilerplate.network.models.Repository;
import com.wontak.boilerplate.network.models.User;

import java.util.ArrayList;
import java.util.List;

public class NetworkModelConverter
{
    public static com.wontak.boilerplate.domain.models.User convertToDomainModel(User user)
    {
        com.wontak.boilerplate.domain.models.User toReturn = new com.wontak.boilerplate.domain.models.User();

        toReturn.username = user.login;
        toReturn.name = user.name;
        toReturn.email = user.email;

        toReturn.avatarUrl = user.avatarUrl;

        toReturn.followers = user.followers;
        toReturn.following = user.following;

        toReturn.createdAt = user.createdAt;

        return toReturn;
    }

    public static List<com.wontak.boilerplate.domain.models.Repository> convertToDomainModel(List<Repository> repositories)
    {
        List<com.wontak.boilerplate.domain.models.Repository> toReturn = new ArrayList();

        for (Repository repository : repositories)
        {
            toReturn.add(convertToDomainModel(repository));
        }

        return toReturn;
    }

    public static com.wontak.boilerplate.domain.models.Repository convertToDomainModel(Repository repository)
    {
        com.wontak.boilerplate.domain.models.Repository toReturn = new com.wontak.boilerplate.domain.models.Repository();

        toReturn.id = repository.id;

        toReturn.name = repository.name;
        toReturn.url = repository.url;

        return toReturn;
    }
}
