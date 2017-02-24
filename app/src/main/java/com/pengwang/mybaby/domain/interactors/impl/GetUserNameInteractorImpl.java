package com.pengwang.mybaby.domain.interactors.impl;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.GetUserNameInteractor;
import com.pengwang.mybaby.domain.interactors.base.AbstractInteractor;
import com.pengwang.mybaby.domain.repository.SharaPreferencesRepository;

/**
 * Created by Peng on 2/24/2017.
 * Implement for GetUserNameInterator
 */
public class GetUserNameInteractorImpl extends AbstractInteractor implements GetUserNameInteractor {
    private static final String ILLEGAL_ARGUMENT = "Illegal Argument!";
    private SharaPreferencesRepository repository;
    private Callback callback;

    public GetUserNameInteractorImpl(Executor executor, MainThread mainThread, SharaPreferencesRepository
            repository,Callback callback) {
        super(executor, mainThread);
        if (repository==null || callback==null) throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        this.repository=repository;
        this.callback=callback;
    }

    @Override
    public void run() {
        final String username=repository.getUserName();

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onUsernameRetrieved(username);
            }
        });

    }
}
