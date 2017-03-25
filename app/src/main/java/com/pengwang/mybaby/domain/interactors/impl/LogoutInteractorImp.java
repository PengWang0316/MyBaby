package com.pengwang.mybaby.domain.interactors.impl;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.LogoutInteractor;
import com.pengwang.mybaby.domain.interactors.base.AbstractInteractor;
import com.pengwang.mybaby.domain.repository.SharePreferencesRepository;

/**
 * Created by Peng on 3/24/2017.
 * The implementation of LogoutInteractor.
 */

public class LogoutInteractorImp extends AbstractInteractor implements LogoutInteractor {
    private static final String ILLEGAL_ARGUMENT = "Illegal Argument!";
    //private DatabaseRepository databaseRepository;
    private SharePreferencesRepository sharePreferencesRepository;
    private Callback callback;

    public LogoutInteractorImp(Executor executor, MainThread mainThread, SharePreferencesRepository
            sharePreferencesRepository, Callback callback) {
        super(executor, mainThread);
        if (callback == null || sharePreferencesRepository == null)
            throw new IllegalArgumentException
                    (ILLEGAL_ARGUMENT);
//        this.databaseRepository = repository;
        this.callback = callback;
        this.sharePreferencesRepository = sharePreferencesRepository;
    }

    @Override
    public void run() {
        sharePreferencesRepository.removeUser();

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onLogout();
            }
        });
    }
}
