package com.wontak.sample.data.converters;

import com.wontak.sample.data.models.User;

public class UserModelConverter {

    public static com.wontak.sample.domain.models.User convertToDomainModel(User user) {
        com.wontak.sample.domain.models.User toReturn = new com.wontak.sample.domain.models.User();

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
