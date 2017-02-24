package com.pengwang.mybaby.dagger.modules;

import com.pengwang.mybaby.dagger.scopes.LoginActivityScope;
import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.impl.GetUserNameInteractorImpl;
import com.pengwang.mybaby.domain.repository.SharaPreferencesRepository;
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

    public LoginActivityModule(LoginPresenter.View view){
        this.view=view;
    }

    @Provides
    @LoginActivityScope
    LoginPresenter.View getView(){
        return view;
    }

    @Provides
    @LoginActivityScope
    LoginPresenter getLoginPresenter(Executor executor, MainThread mainThread, LoginPresenter.View view,
                                     SharaPreferencesRepository sharaPreferencesRepository){
        LoginPresenterImpl presenter=new LoginPresenterImpl(executor,mainThread,view);
        presenter.setGetUserNameInteractor(new GetUserNameInteractorImpl(executor,mainThread,
                sharaPreferencesRepository,presenter));
        return presenter;
    }
}
