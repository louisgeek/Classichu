package com.louisgeek.classichu.classic;

/**
 * Created by louisgeek on 2016/12/4.
 */

public class ClassicContract {

    public interface View<D> extends ClassicView<D> {
    }

    public interface Presenter<V> extends ClassicPresenter<V> {
    }

    public interface Model<D> extends ClassicModel<D> {

    }


}