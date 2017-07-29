package com.classichu.classichu.basic.eventbus.factory.impls;

import com.classichu.classichu.basic.eventbus.factory.interfaces.IBus;
import com.classichu.classichu.basic.rxjava.RxBus;

/**
 * Created by Classichu on 2017-6-5.
 */

public class RxBusImpl implements IBus {
    @Override
    public void register(Object subscriber) {
        // TODO: 2017-6-15
    }

    @Override
    public void unregister(Object subscriber) {
        // TODO: 2017-6-15
    }

    @Override
    public void post(Object event) {
        RxBus.getInstance().post(event);
    }

    @Override
    public void postSticky(Object event) {
        // TODO: 2017-6-15
    }
}
