package com.wontak.boilerplate.presentation.ui.listeners;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBus
{
    private final Subject<Object, Object> bus = new SerializedSubject(PublishSubject.create());

    public void post(Object event)
    {
        bus.onNext(event);
    }

    public Observable<Object> toObservable()
    {
        return bus;
    }

    public boolean hasObservers()
    {
        return bus.hasObservers();
    }
}
