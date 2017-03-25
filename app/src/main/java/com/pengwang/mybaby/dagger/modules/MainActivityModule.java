package com.pengwang.mybaby.dagger.modules;

import android.app.Activity;

import com.pengwang.mybaby.dagger.scopes.MainActivityScope;
import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.impl.GetInitialRecordsInteractorImpl;
import com.pengwang.mybaby.domain.interactors.impl.LogoutInteractorImp;
import com.pengwang.mybaby.domain.repository.RecordRepository;
import com.pengwang.mybaby.domain.repository.SharePreferencesRepository;
import com.pengwang.mybaby.presentation.presenters.MainPresenter;
import com.pengwang.mybaby.presentation.presenters.impl.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Peng on 2/19/2017.
 * The module for main activity (presenters, interactor)
 */
@Module
public class MainActivityModule {
    private MainPresenter.View view;
    private Activity activity;

    public MainActivityModule(MainPresenter.View view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }

    //    Clean architecture requires use interface to reduce dependency.
//    So, here cannot use instructor inject.
//    MainPresenterImpl has to be initiated manually.
    @Provides
    @MainActivityScope
    MainPresenter mainPresenter(Executor executor, MainThread mainThread, MainPresenter.View view, RecordRepository
            recordRepository, SharePreferencesRepository sharePreferencesRepository) {
        MainPresenterImpl mainPresenter = new MainPresenterImpl(executor, mainThread, view);
//        set all interactor here in order to test.
        mainPresenter.setGetInitialRecordsInteractor(new GetInitialRecordsInteractorImpl(executor, mainThread,
                recordRepository, mainPresenter));
        mainPresenter.setLogoutInteractor(new LogoutInteractorImp(executor,mainThread,sharePreferencesRepository,mainPresenter));
        return mainPresenter;
    }

    @Provides
    @MainActivityScope
    MainPresenter.View getView() {
        return view;
    }

    @Provides
    @MainActivityScope
    Activity getActivity() {
        return activity;
    }


}
