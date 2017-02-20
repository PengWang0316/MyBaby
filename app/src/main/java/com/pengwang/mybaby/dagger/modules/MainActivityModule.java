package com.pengwang.mybaby.dagger.modules;

import com.pengwang.mybaby.dagger.scopes.MainActivityScope;
import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.repository.RecordRepository;
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

    public MainActivityModule(MainPresenter.View view) {
        this.view = view;
    }

//    Clean architecture requires use interface to reduce dependency.
//    So, here cannot use instructor inject.
//    MainPresenterImpl has to be initiated manually.
    @Provides
    @MainActivityScope
    MainPresenter mainPresenter(Executor executor, MainThread mainThread, MainPresenter.View view, RecordRepository
            recordRepository) {
        return new MainPresenterImpl(executor, mainThread, view, recordRepository);
    }

    @Provides
    @MainActivityScope
    MainPresenter.View getView() {
        return view;
    }


}
