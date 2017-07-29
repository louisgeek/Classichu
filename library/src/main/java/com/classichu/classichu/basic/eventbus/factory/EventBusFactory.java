package com.classichu.classichu.basic.eventbus.factory;

import com.classichu.classichu.basic.eventbus.factory.impls.EventBusImpl;
import com.classichu.classichu.basic.eventbus.factory.impls.RxBusImpl;
import com.classichu.classichu.basic.eventbus.factory.interfaces.IBus;

/**
 * Created by Classichu on 2017-6-5.
 */

public class EventBusFactory {

    public  static IBus getEventBusManager(){
        return EventBusManagerFactoryInner4EventBusImpl.INSTANCE;
    }

    private  static  class  EventBusManagerFactoryInner4EventBusImpl {
        private static final EventBusImpl INSTANCE = new EventBusImpl();
    }
    private  static  class  EventBusManagerFactoryInner4RxBusImpl {
        private static final RxBusImpl INSTANCE = new RxBusImpl();
    }

}
