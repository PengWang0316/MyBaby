package com.pengwang.mybaby.domain.executor.Impl;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.interactors.Interactor;
import com.pengwang.mybaby.domain.interactors.base.AbstractInteractor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Peng on 2/12/2017.
 * Use ThreadPoolExecutor to run the code in background
 */

public class  ThreadExecutor implements Executor {

    //    Keep it singleton
    private static volatile ThreadExecutor mThreadExecutor;

    //    The variables for ThreadPoolExecutor
    private final static int CORE_POOL_NUMBER = 3;
    private final static int MAX_POOL_NUMBER = 5;
    private final static long KEEP_ALIVE_TIME = 120;
    private final static TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private final static BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>();


    private ThreadPoolExecutor mThreadPoolExecutor;


    private ThreadExecutor() {
        mThreadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_NUMBER, MAX_POOL_NUMBER, KEEP_ALIVE_TIME, TIME_UNIT,
                WORK_QUEUE);
    }

    //    Run interactor's code in background
    @Override
    public void execute(final Interactor interactor) {
        mThreadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                interactor.run();
//                Call the finish method after run
                interactor.onFinished();
            }
        });
    }

    public static Executor getInstance() {
        if (mThreadExecutor == null) mThreadExecutor = new ThreadExecutor();
        return mThreadExecutor;
    }
}
