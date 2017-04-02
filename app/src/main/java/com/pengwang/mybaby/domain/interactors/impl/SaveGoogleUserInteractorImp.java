package com.pengwang.mybaby.domain.interactors.impl;

import android.provider.ContactsContract;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.SaveGoogleUserInteractor;
import com.pengwang.mybaby.domain.interactors.base.AbstractInteractor;
import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.domain.repository.DatabaseRepository;
import com.pengwang.mybaby.domain.repository.SharePreferencesRepository;

/**
 * Created by Peng on 4/1/2017.
 * The Interactor for saving google users
 */

public class SaveGoogleUserInteractorImp extends AbstractInteractor implements SaveGoogleUserInteractor {
    private static final String ILLEGAL_ARGUMENT = "Illegal Argument!";
    private String googleName;
    private String googleId;
    private String googleEmail;

    private DatabaseRepository databaseRepository;
    private SharePreferencesRepository sharePreferencesRepository;
    private SaveGoogleUserInteractor.Callback callback;

    public SaveGoogleUserInteractorImp(Executor executor, MainThread mainThread, DatabaseRepository
            databaseRepository, SharePreferencesRepository sharePreferencesRepository, SaveGoogleUserInteractor
                                               .Callback callback) {
        super(executor, mainThread);
        if (databaseRepository == null || sharePreferencesRepository == null || callback == null) throw new
                IllegalArgumentException(ILLEGAL_ARGUMENT);
        this.databaseRepository = databaseRepository;
        this.sharePreferencesRepository = sharePreferencesRepository;
        this.callback = callback;
    }


    @Override
    public void run() {
        final User user = new User();
        if (googleId != null && googleName != null) {
            user.setGoogleId(googleId);
            user.setName(googleName);
            user.setEmail(googleEmail);
            databaseRepository.saveGoogleUser(user);
            sharePreferencesRepository.saveUser(user);
        }
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onSaveGoogleUser(user);
            }
        });
    }

    @Override
    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    @Override
    public void setGoogleName(String googleName) {
        this.googleName = googleName;
    }

    @Override
    public void setGoogleEmail(String googleEmail) {
        this.googleEmail = googleEmail;
    }
}
