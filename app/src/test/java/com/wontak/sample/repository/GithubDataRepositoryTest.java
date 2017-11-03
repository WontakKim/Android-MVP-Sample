package com.wontak.sample.repository;

import com.wontak.sample.data.repositories.GithubDataRepository;
import com.wontak.sample.data.network.GithubApiService;
import com.wontak.sample.data.models.Repository;
import com.wontak.sample.data.models.User;

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

import static org.mockito.Mockito.*;

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
        when(mockGithubApiService.getUser(FAKE_USER_ID))
                .thenReturn(Observable.just(user));

        mockGithubDataRepository.getUser(FAKE_USER_ID);

        verify(mockGithubApiService).getUser(FAKE_USER_ID);
    }

    public void testGetRepositories() {
        List<Repository> repositories = new ArrayList();
        repositories.add(new Repository());
        when(mockGithubApiService.getUsersRepositories(FAKE_USER_ID))
                .thenReturn(Observable.just(repositories));

        mockGithubDataRepository.getUserRepositories(FAKE_USER_ID);

        verify(mockGithubApiService).getUsersRepositories(FAKE_USER_ID);
    }
}
