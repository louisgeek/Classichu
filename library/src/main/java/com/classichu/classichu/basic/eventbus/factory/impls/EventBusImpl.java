package com.classichu.classichu.basic.eventbus.factory.impls;

import com.classichu.classichu.basic.eventbus.factory.interfaces.IBus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Classichu on 2017-6-5.
 */

public class EventBusImpl implements IBus{
    @Override
    public void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    @Override
    public void post(Object event) {
        EventBus.getDefault().post(event);
    }

    @Override
    public void postSticky(Object event) {
        EventBus.getDefault().postSticky(event);
    }
}
