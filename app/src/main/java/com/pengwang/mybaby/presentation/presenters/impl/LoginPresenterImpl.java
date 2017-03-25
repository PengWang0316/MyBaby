package com.pengwang.mybaby.presentation.presenters.impl;

import android.support.annotation.NonNull;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.GetUserInteractor;
import com.pengwang.mybaby.domain.interactors.SaveFaceBookUserInteractor;
import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.presentation.presenters.LoginPresenter;

/**
 * Created by Peng on 2/24/2017.
 * Presenter for Login Activity
 */

public class LoginPresenterImpl extends AbstractPresenter implements LoginPresenter, GetUserInteractor.Callback,
        SaveFaceBookUserInteractor.Callback {
    private View view;
    private GetUserInteractor getUserInteractor;
    private SaveFaceBookUserInteractor saveFaceBookUserInteractor;

    public LoginPresenterImpl(Executor executor, MainThread mainThread, View view) {
        super(executor, mainThread);
        this.view = view;
    }

    private void showProgress() {
        view.showProgress();
    }

    @Override
    public void checkLoginStatus() {
        showProgress();
        getUserInteractor.execute();
    }

    @Override
    public void saveFacebookUserInformation(String facebookId, String facebookName) {
        showProgress();
        saveFaceBookUserInteractor.setFacebookUserId(facebookId);
        saveFaceBookUserInteractor.setFacebookUserName(facebookName);
        saveFaceBookUserInteractor.execute();
    }


    /*
    *   Callback method from GetUserInteractor
    *   Show main activity and add user name to application if has a username otherwise stay login activity
    */
    @Override
    public void onUserRetrieved(@NonNull User user) {
        hideProgress();
        if (user.getName() != null && user.getId() != null) {
            view.setUserToApplication(user);
            view.showMainActivity();
        }
    }

    private void hideProgress() {
        view.hideProgress();
    }

    @Override
    public void resume() {
        checkLoginStatus();
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {


    }

    @Override
    public void onError(String message) {

    }


    public void setGetUserInteractor(GetUserInteractor getUserInteractor) {
        this.getUserInteractor = getUserInteractor;
    }

    public void setSaveFaceBookUserInteractor(SaveFaceBookUserInteractor saveFaceBookUserInteractor) {
        this.saveFaceBookUserInteractor = saveFaceBookUserInteractor;
    }

    /*
    *   Callback method from SaveFaceBookUserInteractor
    *
    */
    @Override
    public void onSaveFaceBookUser(User user) {
        onUserRetrieved(user);
    }
}
