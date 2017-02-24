package com.pengwang.mybaby.presentation.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pengwang.mybaby.R;
import com.pengwang.mybaby.application.MyApplication;
import com.pengwang.mybaby.dagger.componets.DaggerLoginActivityComponent;
import com.pengwang.mybaby.dagger.componets.LoginActivityComponent;
import com.pengwang.mybaby.dagger.modules.LoginActivityModule;
import com.pengwang.mybaby.presentation.presenters.LoginPresenter;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initiate();
    }

//  Initiate dagger dependency injection
    private void initiate() {
        LoginActivityComponent component= DaggerLoginActivityComponent.builder().loginActivityModule(new
                LoginActivityModule(this)).applicationComponent(MyApplication.getApplication(this)
                .getApplicationComponent()).build();
        component.inject(this);
    }

    //Show main activity after find user name
    @Override
    public void showMainActivity() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Set user name to application
    @Override
    public void setUsernameToApplication(String username) {
        ((MyApplication) getApplication()).setUsername(username);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginPresenter.resume();
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void ShowError(String message) {

    }
}
