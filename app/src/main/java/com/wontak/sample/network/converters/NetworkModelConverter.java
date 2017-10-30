package com.wontak.sample.network.converters;

import com.wontak.sample.network.models.Repository;
import com.wontak.sample.network.models.User;

import java.util.ArrayList;
import java.util.List;

public class NetworkModelConverter {

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

    public static List<com.wontak.sample.domain.models.Repository> convertToDomainModel(List<Repository> repositories) {
        List<com.wontak.sample.domain.models.Repository> toReturn = new ArrayList();

        for (Repository repository : repositories) {
            toReturn.add(convertToDomainModel(repository));
        }

        return toReturn;
    }

    public static com.wontak.sample.domain.models.Repository convertToDomainModel(Repository repository) {
        com.wontak.sample.domain.models.Repository toReturn = new com.wontak.sample.domain.models.Repository();

        toReturn.id = repository.id;

        toReturn.name = repository.name;
        toReturn.url = repository.url;
        toReturn.htmlUrl = repository.htmlUrl;

        return toReturn;
    }
}
