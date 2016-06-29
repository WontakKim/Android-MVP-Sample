package com.wontak.boilerplate.presentation.presenters;

import com.wontak.boilerplate.domain.interactors.GetUserUseCase;
import com.wontak.boilerplate.domain.models.User;
import com.wontak.boilerplate.presentation.ui.listeners.ResultView;

import javax.inject.Inject;

import rx.Subscriber;

public class ResultPresenter
{
    private GetUserUseCase getUserUseCase;

    private ResultView.View view;

    @Inject
    public ResultPresenter(GetUserUseCase getUserUseCase)
    {
        this.getUserUseCase = getUserUseCase;
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

    private void showUserDetailsInView(User user)
    {
        view.renderUser(user);
    }

    private final class GetUserSubscriber extends Subscriber<User>
    {
        @Override
        public void onCompleted()
        {
            // view.hideLoading();
        }

        @Override
        public void onError(Throwable e)
        {

        }

        @Override
        public void onNext(User user)
        {
            showUserDetailsInView(user);
        }
    }
}
