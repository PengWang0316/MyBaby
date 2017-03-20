package com.pengwang.mybaby.application;

import android.app.Activity;
import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.pengwang.mybaby.dagger.componets.ApplicationComponent;
import com.pengwang.mybaby.dagger.componets.DaggerApplicationComponent;
import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.models.User;

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

    private User user;

    @Override
    public void onCreate() {
        super.onCreate();

        initiateDaggerComponent();
        initiateFacebookLogging();
    }

    private void initiateFacebookLogging() {
//        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
