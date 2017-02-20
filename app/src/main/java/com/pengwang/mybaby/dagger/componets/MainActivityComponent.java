package com.pengwang.mybaby.dagger.componets;

import com.pengwang.mybaby.dagger.modules.MainActivityModule;
import com.pengwang.mybaby.dagger.modules.RecordRepositoryModule;
import com.pengwang.mybaby.dagger.scopes.MainActivityScope;
import com.pengwang.mybaby.presentation.presenters.MainPresenter;
import com.pengwang.mybaby.presentation.ui.activities.MainActivity;

import dagger.Component;

/**
 * Created by Peng on 2/19/2017.
 * MainActivity component for dagger
 * Depends on ApplicationComponent
 */
@MainActivityScope
@Component (modules = {RecordRepositoryModule.class, MainActivityModule.class}, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    void injectMainActivity (MainActivity mainActivity);
}
