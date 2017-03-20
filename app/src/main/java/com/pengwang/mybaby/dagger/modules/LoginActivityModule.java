package com.pengwang.mybaby.dagger.modules;

import android.app.Activity;

import com.facebook.CallbackManager;
import com.pengwang.mybaby.dagger.scopes.LoginActivityScope;
import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.impl.GetUserInteractorImpl;
import com.pengwang.mybaby.domain.interactors.impl.SaveFaceBookUserInteractorImp;
import com.pengwang.mybaby.domain.repository.DatabaseRepository;
import com.pengwang.mybaby.domain.repository.SharePreferencesRepository;
import com.pengwang.mybaby.presentation.presenters.LoginPresenter;
import com.pengwang.mybaby.presentation.presenters.impl.LoginPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Peng on 2/24/2017.
 * The module for Login activity
 */
@LoginActivityScope
@Module
public class LoginActivityModule {
    private LoginPresenter.View view;
    private Activity activity;

    public LoginActivityModule(LoginPresenter.View view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Provides
    @LoginActivityScope
    LoginPresenter.View getView() {
        return view;
    }

    @Provides
    @LoginActivityScope
    Activity getActivity() {
        return activity;
    }

    @Provides
    @LoginActivityScope
    LoginPresenter getLoginPresenter(Executor executor, MainThread mainThread, LoginPresenter.View view,
                                     SharePreferencesRepository sharePreferencesRepository, DatabaseRepository databaseRepository) {
        LoginPresenterImpl presenter = new LoginPresenterImpl(executor, mainThread, view);
        presenter.setGetUserInteractor(new GetUserInteractorImpl(executor, mainThread,
                sharePreferencesRepository, presenter));
        presenter.setSaveFaceBookUserInteractor(new SaveFaceBookUserInteractorImp(executor,mainThread,
                databaseRepository, sharePreferencesRepository, presenter));
        return presenter;
    }

    @Provides
    @LoginActivityScope
    CallbackManager getCallbackManager() {
        return CallbackManager.Factory.create();
    }


}
