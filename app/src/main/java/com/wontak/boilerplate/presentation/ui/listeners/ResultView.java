package com.wontak.boilerplate.presentation.ui.listeners;

import android.content.Context;

import com.wontak.boilerplate.domain.models.User;
import com.wontak.boilerplate.presentation.models.RepositoryItem;

import java.util.List;

public interface ResultView
{
    interface RecyclerViewClickListener
    {
        void onViewClick(int index);
    }

    interface View
    {
        Context context();

        void onRepositoryClick(RepositoryItem repositoryItem);

        void showUser(User user);
        void showRepositories(List<RepositoryItem> repositoryItems);

        void showLoading();
        void hideLoading();

        void showError(String message);
    }
}
