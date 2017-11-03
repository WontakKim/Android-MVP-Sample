package com.wontak.sample.presentation.presenters;

import com.wontak.sample.domain.interactors.GetUserRepositoriesUseCase;
import com.wontak.sample.domain.interactors.GetUserUseCase;
import com.wontak.sample.presentation.ui.listeners.ResultView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ResultPresenterTest {

    private static final String FAKE_USERNAME = "abcd";

    private ResultPresenter resultPresenter;

    @Mock private ResultView.View mockResultView;
    @Mock private GetUserUseCase mockGetUserUseCase;
    @Mock private GetUserRepositoriesUseCase mockGetUserRepositoriesUseCase;

    @Before
    public void setUp() throws Exception {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(callable -> Schedulers.trampoline());
        resultPresenter = new ResultPresenter(mockGetUserUseCase, mockGetUserRepositoriesUseCase);
        resultPresenter.setView(mockResultView);
    }

    @Test
    public void testDestroy() throws Exception {
        resultPresenter.destroy();

        verify(mockGetUserUseCase).dispose();
        verify(mockGetUserRepositoriesUseCase).dispose();
    }

    @Test
    public void testGetUser() throws Exception {
        resultPresenter.getUser(FAKE_USERNAME);

        verify(mockGetUserUseCase).execute(any(DisposableObserver.class), eq(FAKE_USERNAME));
    }

    @Test
    public void testGetUserRepositories() throws Exception {
        resultPresenter.getUserRepositories(FAKE_USERNAME);

        verify(mockGetUserRepositoriesUseCase).execute(any(DisposableObserver.class), eq(FAKE_USERNAME));
    }

}