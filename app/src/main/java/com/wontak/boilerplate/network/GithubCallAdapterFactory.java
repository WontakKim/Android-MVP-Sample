package com.wontak.boilerplate.network;

import com.wontak.boilerplate.network.exceptions.NetworkConnectionException;
import com.wontak.boilerplate.network.exceptions.UserNotFoundException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;

public class GithubCallAdapterFactory extends CallAdapter.Factory
{
    private final RxJavaCallAdapterFactory original;

    private GithubCallAdapterFactory()
    {
        original = RxJavaCallAdapterFactory.create();
    }

    public static CallAdapter.Factory create()
    {
        return new GithubCallAdapterFactory();
    }

    @Override
    public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit)
    {
        return new RxCallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit));
    }

    private static class RxCallAdapterWrapper implements CallAdapter<Observable<?>>
    {
        private final Retrofit retrofit;
        private final CallAdapter<?> wrapped;

        public RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<?> wrapped)
        {
            this.retrofit = retrofit;
            this.wrapped = wrapped;
        }

        @Override
        public Type responseType()
        {
            return wrapped.responseType();
        }

        @SuppressWarnings("unchecked")
        @Override
        public <R> Observable<?> adapt(Call<R> call)
        {
            return ((Observable<R>) wrapped.adapt(call))
                    .onErrorResumeNext(throwable -> Observable.error(asGithubException(throwable)));
        }

        private Exception asGithubException(Throwable throwable)
        {
            if (throwable instanceof HttpException)
            {
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                switch (response.code())
                {
                    case 404: return new UserNotFoundException();
                }
            }

            return new NetworkConnectionException(throwable.getCause());
        }
    }
}