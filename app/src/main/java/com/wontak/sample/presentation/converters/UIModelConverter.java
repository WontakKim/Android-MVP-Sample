package com.wontak.sample.presentation.converters;

import com.wontak.sample.domain.models.Repository;
import com.wontak.sample.presentation.models.RepositoryItem;

import java.util.ArrayList;
import java.util.List;

public class UIModelConverter {

    public static List<RepositoryItem> convertToUIModel(final List<Repository> repositories) {
        List<RepositoryItem> toReturn = new ArrayList();

        for (Repository repository : repositories) {
            toReturn.add(convertToUIModel(repository));
        }

        return toReturn;
    }

    public static RepositoryItem convertToUIModel(final Repository repository) {
        RepositoryItem toReturn = new RepositoryItem();

        toReturn.name = repository.name;
        toReturn.url = repository.url;
        toReturn.htmlUrl = repository.htmlUrl;

        return toReturn;
    }
}
