package com.wontak.boilerplate.network.converters;

import com.wontak.boilerplate.network.models.User;

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
}
