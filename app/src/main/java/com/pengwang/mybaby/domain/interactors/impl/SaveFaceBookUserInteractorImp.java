package com.pengwang.mybaby.domain.interactors.impl;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.SaveFaceBookUserInteractor;
import com.pengwang.mybaby.domain.interactors.base.AbstractInteractor;
import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.domain.repository.DatabaseRepository;
import com.pengwang.mybaby.domain.repository.SharePreferencesRepository;

/**
 * Created by Peng on 3/19/2017.
 * For save Facebook user to database
 */

public class SaveFaceBookUserInteractorImp extends AbstractInteractor implements SaveFaceBookUserInteractor {
    private static final String ILLEGAL_ARGUMENT = "Illegal Argument!";
    private DatabaseRepository databaseRepository;
    private SharePreferencesRepository sharePreferencesRepository;

    private Callback callback;
    private String facebookUserName;
    private String facebookUserId;

    public SaveFaceBookUserInteractorImp(Executor executor, MainThread mainThread, DatabaseRepository repository,
                                         SharePreferencesRepository sharePreferencesRepository,
                                         SaveFaceBookUserInteractor.Callback callback) {
        super(executor, mainThread);
        if (repository==null || callback==null || sharePreferencesRepository==null) throw new IllegalArgumentException
                (ILLEGAL_ARGUMENT);
        this.databaseRepository =repository;
        this.callback=callback;
        this.sharePreferencesRepository=sharePreferencesRepository;
    }

    @Override
    public void run() {
//        1. check or create user to the database.
//        2. save user to the share preference file.
        final User user=new User();
        if(facebookUserId!=null && facebookUserName!=null){

            user.setName(facebookUserName);
            user.setFacebookId(facebookUserId);
            databaseRepository.saveFacebookUser(user);
            sharePreferencesRepository.saveUser(user);
        }

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onSaveFaceBookUser(user);
            }
        });
    }

    @Override
    public void setFacebookUserId(String facebookUserId) {
        this.facebookUserId = facebookUserId;
    }
    @Override
    public void setFacebookUserName(String facebookUserName) {
        this.facebookUserName = facebookUserName;
    }
}
