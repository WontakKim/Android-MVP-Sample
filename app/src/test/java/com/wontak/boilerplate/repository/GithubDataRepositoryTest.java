package com.wontak.boilerplate.repository;

import com.wontak.boilerplate.data.repositories.GithubDataRepository;
import com.wontak.boilerplate.network.GithubApiService;
import com.wontak.boilerplate.network.models.Repository;
import com.wontak.boilerplate.network.models.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)
public class GithubDataRepositoryTest {

    private static final String FAKE_USER_ID = "abcd";

    private GithubDataRepository mockGithubDataRepository;

    @Mock private GithubApiService mockGithubApiService;

    @Before
    public void setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(callable -> Schedulers.trampoline());
        mockGithubDataRepository = new GithubDataRepository(mockGithubApiService);
    }

    @Test
    public void testGetUser() {
        User user = new User();
        Mockito.when(mockGithubApiService.getUser(FAKE_USER_ID))
                .thenReturn(Observable.just(user));

        mockGithubDataRepository.getUser(FAKE_USER_ID);

        Mockito.verify(mockGithubApiService).getUser(FAKE_USER_ID);
    }

    public void testGetRepositories() {
        List<Repository> repositories = new ArrayList();
        repositories.add(new Repository());
        Mockito.when(mockGithubApiService.getUsersRepositories(FAKE_USER_ID))
                .thenReturn(Observable.just(repositories));

        mockGithubDataRepository.getUserRepositories(FAKE_USER_ID);

        Mockito.verify(mockGithubApiService).getUsersRepositories(FAKE_USER_ID);
    }
}
