package com.pengwang.mybaby.domain.interactors.base;


import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.Interactor;

/**
 * Created by Peng on 2/12/2017.
 * Offering the basic code for all interactor such as run, check and cancel.
 * Before an interactor run the code, check whether it has already run.
 * If an activity will be destroyed, cancel the interactor action.
 */

public abstract class AbstractInteractor implements Interactor {
    private Executor mExecutor;
    protected MainThread mMainThread;

    //Variables for status
    private volatile boolean isRunning;
    private volatile boolean isCanceled;

    public AbstractInteractor(Executor executor, MainThread mainThread) {
        mExecutor = executor;
        mMainThread = mainThread;
    }

    //When run a test, call this method directly.
    public abstract void run();

    public boolean isRunning() {
        return isRunning;
    }

    //Cancel the task
    public void cancel() {

        isRunning = false;
        isCanceled = true;

    }

    //Call this method when executor finish its task
    public void onFinished() {
        isRunning = false;
        isCanceled = false;
    }

    @Override
    public void execute() {
//        Mark it is running now
        this.isRunning = true;
//        Run the task in a background executor(thread)
        mExecutor.execute(this);
    }
}
