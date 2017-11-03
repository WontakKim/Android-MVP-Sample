package com.wontak.sample.domain.interactors;

import com.wontak.sample.domain.repositories.GithubRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetUserUseCaseTest {

    private GetUserUseCase getUserUseCase;

    @Mock
    private GithubRepository mockGithubRepository;

    @Before
    public void setUp() throws Exception {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(callable -> Schedulers.trampoline());
        getUserUseCase = new GetUserUseCase(mockGithubRepository);
    }

    @Test
    public void testUseCaseObservable() {
        getUserUseCase.buildUseCaseObservable(anyString());

        verify(mockGithubRepository).getUser(anyString());
        verifyNoMoreInteractions(mockGithubRepository);
    }
}