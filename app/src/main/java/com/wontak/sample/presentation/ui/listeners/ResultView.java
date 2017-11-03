package com.wontak.sample.presentation.ui.listeners;

import android.content.Context;

import com.wontak.sample.domain.models.Repository;
import com.wontak.sample.domain.models.User;

import java.util.List;

public interface ResultView {

    interface RecyclerViewClickListener {

        void onViewClick(int index);
    }

    interface View {

        Context context();

        void onRepositoryClick(Repository repository);

        void showUser(User user);

        void showRepositories(List<Repository> repositories);

        void showLoading();

        void hideLoading();

        void showError(String message);
    }
}
