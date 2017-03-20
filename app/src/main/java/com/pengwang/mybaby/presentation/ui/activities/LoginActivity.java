package com.pengwang.mybaby.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.pengwang.mybaby.R;
import com.pengwang.mybaby.application.MyApplication;
import com.pengwang.mybaby.dagger.componets.DaggerLoginActivityComponent;
import com.pengwang.mybaby.dagger.componets.LoginActivityComponent;
import com.pengwang.mybaby.dagger.modules.LoginActivityModule;
import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.presentation.presenters.LoginPresenter;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {

    private final static String TAG=LoginActivity.class.getName();

    @Inject
    LoginPresenter loginPresenter;

    @Inject
    CallbackManager callbackManager;

    private ProfileTracker profileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initiateDagger();
//        TODO check the login status before initialize Facebook and Google login buttons and callback
//        Now the check and forward code are put in the Resume method.

//        Register the callback for login button
        registerFacebookLoginCallback();

//        registerFacebookLogging();
    }

    //Register the callback object for the Facebook login button
    private void registerFacebookLoginCallback() {
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
//                Log.d("dd", ">>>>>>>>>>>>> onCurrentProfileChanged");
                if (currentProfile != null) {
//                    Login success. Save the name and id to the SharePreference and Application's User object.
                    Log.d(TAG, ">>>>>>>>>>>>>>>>>>" + currentProfile.getName());
                    Log.d(TAG, ">>>>>>>>>>>>>>>>>>" + currentProfile.getId());
                    Log.d(TAG, ">>>>>>>>>>>>>>>>>>" + currentProfile.getLinkUri());
                    Log.d(TAG, ">>>>>>>>>>>>>>>>>>" + currentProfile.getProfilePictureUri(32, 32));
                    loginPresenter.saveFacebookUserInformation(currentProfile.getId(), currentProfile.getName());
                }
            }
        };
        profileTracker.startTracking();

        final LoginButton loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "Facebook callback-------Success ");
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "Facebook callback-------Cancel ");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("dd", "Facebook callback-------Error");
            }
        });
    }

    //    Process Facebook logging
//    After login success, should create a account to our application and save it to share Preference
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("dd", ">>>>>>>>>>>  onActivityResult  <<<<<<<<<<<<");
        if (Profile.getCurrentProfile() != null) Log.d(TAG, "2-------" + Profile.getCurrentProfile().getName());
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (Profile.getCurrentProfile() != null) Log.d(TAG, "3-------" + Profile.getCurrentProfile().getName());
    }

    /*
    private void registerFacebookLogging() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("T", "-----------" + loginResult.getAccessToken().getUserId() + "------" + loginResult
                                .getRecentlyGrantedPermissions());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }
    */

    //  Initiate dagger dependency injection
    private void initiateDagger() {
        LoginActivityComponent component = DaggerLoginActivityComponent.builder().loginActivityModule(new
                LoginActivityModule(this, this)).applicationComponent(MyApplication.getApplication(this)
                .getApplicationComponent()).build();
        component.inject(this);
    }

    //Show main activity after find user name
    @Override
    public void showMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Set user name to application
    @Override
    public void setUserToApplication(User user) {
        ((MyApplication) getApplication()).setUser(user);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }
}
