package com.pengwang.mybaby.domain.interactors.impl;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.GetUserInteractor;
import com.pengwang.mybaby.domain.interactors.base.AbstractInteractor;
import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.domain.repository.SharePreferencesRepository;

/**
 * Created by Peng on 2/24/2017.
 * Implement for GetUserInteractor
 */
public class GetUserInteractorImpl extends AbstractInteractor implements GetUserInteractor {
    private static final String ILLEGAL_ARGUMENT = "Illegal Argument!";
    private SharePreferencesRepository repository;
    private Callback callback;

    public GetUserInteractorImpl(Executor executor, MainThread mainThread, SharePreferencesRepository
            repository, Callback callback) {
        super(executor, mainThread);
        if (repository==null || callback==null) throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        this.repository=repository;
        this.callback=callback;
    }

    @Override
    public void run() {
        final User user=repository.getUser();

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onUserRetrieved(user);
            }
        });

    }
}
