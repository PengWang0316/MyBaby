package com.pengwang.mybaby.domain.executor;

import com.pengwang.mybaby.domain.interactors.Interactor;

/**
 * Created by Peng on 2/12/2017.
 * The Executor interface
 */
public interface Executor {
    void execute(final Interactor interactor);
}
