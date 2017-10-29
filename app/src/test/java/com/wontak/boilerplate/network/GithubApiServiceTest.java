package com.wontak.boilerplate.network;

import com.wontak.boilerplate.network.models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GithubApiServiceTest {

    private static final String FAKE_USER_NAME = "user";

    private MockWebServer mockWebServer;
    private GithubApiService mockGithubApiService;

    @Before
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();


        mockGithubApiService = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GithubApiService.class);
    }

    @Test
    public void testGetUserSuccessResponse() throws Exception {
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setBody(makeMockGetUserJsonString())
        );

        TestObserver<User> observer = mockGithubApiService.getUser(FAKE_USER_NAME).test();
        observer.awaitTerminalEvent();

        observer.assertNoErrors();

        User user = observer.values().get(0);
        assertEquals(FAKE_USER_NAME, user.login);
        assertEquals(FAKE_USER_NAME, user.name);
        assertEquals("a@a.com", user.email);
        assertEquals("https://aaa.com", user.avatarUrl);
        assertEquals(0, user.followers);
        assertEquals(1, user.following);
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    private String makeMockGetUserJsonString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("\"login\": \"" + FAKE_USER_NAME + "\",");
        builder.append("\"name\": \"" + FAKE_USER_NAME + "\",");
        builder.append("\"email\": \"a@a.com\",");
        builder.append("\"avatar_url\": \"https://aaa.com\",");
        builder.append("\"followers\": 0,");
        builder.append("\"following\": 1");
        builder.append("}");
        return builder.toString();
    }
}
