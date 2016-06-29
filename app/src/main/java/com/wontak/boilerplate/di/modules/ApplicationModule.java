package com.wontak.boilerplate.di.modules;

import android.app.Application;
import android.content.Context;

import com.wontak.boilerplate.R;
import com.wontak.boilerplate.data.repositories.GithubDataRepository;
import com.wontak.boilerplate.domain.repositories.GithubRepository;
import com.wontak.boilerplate.network.GithubApiService;
import com.wontak.boilerplate.presentation.ui.listeners.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule
{
    private final Application application;

    public ApplicationModule(Application application)
    {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication()
    {
        return application;
    }

    @Provides
    @Singleton
    public Context provideContext()
    {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public RxBus provideRxBus()
    {
        return new RxBus();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        return client;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Application application, OkHttpClient client)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(application.getResources().getString(R.string.endpoint))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public GithubApiService provideGithubApiService(Retrofit retrofit)
    {
        return retrofit.create(GithubApiService.class);
    }

    @Provides
    @Singleton
    public GithubRepository provideGithubRepository(GithubDataRepository githubDataRepository)
    {
        return githubDataRepository;
    }
}
