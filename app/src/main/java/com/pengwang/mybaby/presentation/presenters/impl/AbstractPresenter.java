package com.pengwang.mybaby.presentation.presenters.impl;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.presentation.presenters.BasePresenter;

/**
 * Created by Peng on 2/12/2017.
 * Abstract class for all presenter.
 */

abstract class AbstractPresenter implements BasePresenter {
    MainThread mMainThread;
    Executor mExecutor;

    AbstractPresenter(Executor executor, MainThread mainThread){
        mMainThread=mainThread;
        mExecutor=executor;
    }

}
