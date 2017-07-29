package com.classichu.classichu.basic.rxjava;

import io.reactivex.Flowable;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Classichu on 2017-6-5.
 */
@Deprecated //待优化
public class RxBus {

    private static volatile RxBus instance;
    /*注释说是Thread-safe但不是serialized的*/
    private final PublishProcessor<Object> mPublishProcessor;

    // PublishSubject只会把在订阅发生的时间点之后的数据发射给观察者
    public RxBus() {
        PublishSubject.create();
        mPublishProcessor = PublishProcessor.create();
    }

    // 单例RxBus
    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    // 发送一个新的事件
    public void post(Object o) {
        mPublishProcessor.onNext(o);
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Flowable<T> toFlowable(Class<T> eventType) {
        return mPublishProcessor.ofType(eventType);
    }

    /**
     * 注意由于Subject同时实现了Observable和Observer两个接口，所以可以转换
     * @return
     */
    public Flowable<Object> toFlowable() {
        return mPublishProcessor;
    }

    public boolean hasSubscribers() {
        return mPublishProcessor.hasSubscribers();
    }


}
