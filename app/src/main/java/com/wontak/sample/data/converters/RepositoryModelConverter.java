package com.wontak.sample.data.converters;

import com.wontak.sample.data.models.Repository;

import java.util.ArrayList;
import java.util.List;

public class RepositoryModelConverter {

    public static com.wontak.sample.domain.models.Repository convertToDomainModel(Repository repository) {
        com.wontak.sample.domain.models.Repository toReturn = new com.wontak.sample.domain.models.Repository();

        toReturn.id = repository.id;

        toReturn.name = repository.name;
        toReturn.url = repository.url;
        toReturn.htmlUrl = repository.htmlUrl;

        return toReturn;
    }

    public static List<com.wontak.sample.domain.models.Repository> convertToDomainModel(List<Repository> repositories) {
        List<com.wontak.sample.domain.models.Repository> toReturn = new ArrayList();

        for (Repository repository : repositories) {
            toReturn.add(convertToDomainModel(repository));
        }

        return toReturn;
    }
}
