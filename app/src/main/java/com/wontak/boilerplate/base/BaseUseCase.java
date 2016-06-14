package com.wontak.boilerplate.base;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.Subscriptions;

public abstract class BaseUseCase
{
    private Subscription subscription = Subscriptions.empty();

    private Scheduler subscribeOn;
    private Scheduler observeOn;

    public BaseUseCase(Scheduler subscribeOn, Scheduler observeOn)
    {
        this.subscribeOn = subscribeOn;
        this.observeOn = observeOn;
    }

    protected abstract Observable buildUseCaseObservable();

    public void execute(Subscriber subscriber)
    {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(subscriber);
    }

    public void execute(final Action1 onNext)
    {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(onNext);
    }

    public void execute(final Action1 onNext, final Action1 onError)
    {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(onNext, onError);
    }

    public void execute(final Action1 onNext, final Action1 onError, final Action0 onComplete)
    {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(onNext, onError, onComplete);
    }

    public void unsubscribe()
    {
        if (!subscription.isUnsubscribed())
        {
            subscription.unsubscribe();
        }
    }
}
