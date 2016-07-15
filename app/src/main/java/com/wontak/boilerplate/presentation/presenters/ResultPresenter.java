package com.wontak.boilerplate.presentation.presenters;

import com.wontak.boilerplate.domain.exceptions.DefaultErrorBundle;
import com.wontak.boilerplate.domain.exceptions.ErrorBundle;
import com.wontak.boilerplate.domain.interactors.GetUserRepositoriesUseCase;
import com.wontak.boilerplate.domain.interactors.GetUserUseCase;
import com.wontak.boilerplate.domain.models.Repository;
import com.wontak.boilerplate.domain.models.User;
import com.wontak.boilerplate.presentation.converters.UIModelConverter;
import com.wontak.boilerplate.presentation.exceptions.ErrorMessageFactory;
import com.wontak.boilerplate.presentation.ui.listeners.ResultView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class ResultPresenter
{
    private GetUserUseCase getUserUseCase;
    private GetUserRepositoriesUseCase getUserRepositoriesUseCase;

    private ResultView.View view;

    @Inject
    public ResultPresenter(
            GetUserUseCase getUserUseCase,
            GetUserRepositoriesUseCase getUserRepositoriesUseCase
    )
    {
        this.getUserUseCase = getUserUseCase;
        this.getUserRepositoriesUseCase = getUserRepositoriesUseCase;
    }

    public void destroy()
    {
        getUserUseCase.unsubscribe();
        getUserRepositoriesUseCase.unsubscribe();
    }

    public void setView(ResultView.View view)
    {
        this.view = view;
    }

    public void getUser(String username)
    {
        getUserUseCase.setUsername(username);
        getUserUseCase.execute(new GetUserSubscriber());
    }

    public void getUserRepositories(String username)
    {
        getUserRepositoriesUseCase.setUsername(username);
        getUserRepositoriesUseCase.execute(new GetUserRepositoriesSubscriber());
    }

    private void showUserDetailsInView(User user)
    {
        view.showUser(user);
    }

    private void showRepositoriesInView(List<Repository> repositories)
    {
        view.showRepositories(UIModelConverter.convertToUIModel(repositories));
    }

    private void hideViewLoading()
    {
        view.hideLoading();
    }

    private void showErrorMessage(ErrorBundle errorBundle)
    {
        String errorMessage = ErrorMessageFactory.create(view.context(), errorBundle.getException());
        view.showError(errorMessage);
    }

    private final class GetUserSubscriber extends Subscriber<User>
    {
        @Override
        public void onCompleted()
        {

        }

        @Override
        public void onError(Throwable e)
        {
            hideViewLoading();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onNext(User user)
        {
            showUserDetailsInView(user);
        }
    }

    private final class GetUserRepositoriesSubscriber extends Subscriber<List<Repository>>
    {
        @Override
        public void onCompleted()
        {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e)
        {
            hideViewLoading();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onNext(List<Repository> repositories)
        {
            showRepositoriesInView(repositories);
        }
    }
}
