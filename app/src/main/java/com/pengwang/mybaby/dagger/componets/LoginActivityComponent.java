package com.pengwang.mybaby.dagger.componets;

import com.pengwang.mybaby.dagger.modules.LoginActivityModule;
import com.pengwang.mybaby.dagger.scopes.LoginActivityScope;
import com.pengwang.mybaby.presentation.ui.activities.LoginActivity;

import dagger.Component;

/**
 * Created by Peng on 2/24/2017.
 * The component for Login activity
 */
@LoginActivityScope
@Component (modules = {LoginActivityModule.class},dependencies = ApplicationComponent.class)
public interface LoginActivityComponent {
    void inject(LoginActivity loginActivity);
}
