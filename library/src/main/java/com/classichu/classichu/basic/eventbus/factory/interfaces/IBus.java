package com.classichu.classichu.basic.eventbus.factory.interfaces;

/**
 * Created by Classichu on 2017-6-5.
 */

public interface IBus {

    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);

    void postSticky(Object event);
}
