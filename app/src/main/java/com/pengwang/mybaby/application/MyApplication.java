package com.pengwang.mybaby.application;

import android.app.Activity;
import android.app.Application;

import com.pengwang.mybaby.dagger.componets.ApplicationComponent;
import com.pengwang.mybaby.dagger.componets.DaggerApplicationComponent;
import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;

import javax.inject.Inject;

/**
 * Created by Peng on 2/19/2017.
 * Replace the default application in order to initiate Executor, MainThread, etc.
 */

public class MyApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Inject
    Executor executor;

    @Inject
    MainThread mainThread;

    private String username;

    @Override
    public void onCreate() {
        super.onCreate();
        initiateDaggerComponent();
    }

    private void initiateDaggerComponent() {
        applicationComponent = DaggerApplicationComponent.builder().build();
        applicationComponent.injectMyApplication(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static MyApplication getApplication(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
