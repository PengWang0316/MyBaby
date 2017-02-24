package com.pengwang.mybaby.presentation.presenters.impl;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.GetUserNameInteractor;
import com.pengwang.mybaby.presentation.presenters.LoginPresenter;

/**
 * Created by Peng on 2/24/2017.
 *
 */

public class LoginPresenterImpl extends AbstractPresenter implements LoginPresenter, GetUserNameInteractor.Callback {
    private View view;
    private GetUserNameInteractor getUserNameInteractor;

    public LoginPresenterImpl(Executor executor, MainThread mainThread, View view) {
        super(executor, mainThread);
        this.view=view;
    }

    @Override
    public void checkLoginStatus() {
        view.showProgress();
        getUserNameInteractor.execute();
    }

    @Override
//    show main activity and add user name to application if has a username otherwise stay login activity
    public void onUsernameRetrieved(String username) {
        view.hideProgress();
        if (username!=null){
            view.setUsernameToApplication(username);
            view.showMainActivity();
        }
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


    public void setGetUserNameInteractor(GetUserNameInteractor getUserNameInteractor) {
        this.getUserNameInteractor = getUserNameInteractor;
    }


}
