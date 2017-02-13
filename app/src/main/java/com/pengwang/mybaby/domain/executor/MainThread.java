package com.pengwang.mybaby.domain.executor;

/**
 * Created by Peng on 2/12/2017.
 *Use to do update UI in the main thread. May call by interactors.
 */
public interface MainThread {
    /**
     * @param runnable the code will run in the main thread.
     */
    void post(final Runnable runnable);
}
