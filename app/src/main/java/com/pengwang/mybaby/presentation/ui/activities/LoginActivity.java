package com.pengwang.mybaby.presentation.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.pengwang.mybaby.R;
import com.pengwang.mybaby.application.MyApplication;
import com.pengwang.mybaby.dagger.componets.DaggerLoginActivityComponent;
import com.pengwang.mybaby.dagger.componets.LoginActivityComponent;
import com.pengwang.mybaby.dagger.modules.LoginActivityModule;
import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.presentation.presenters.LoginPresenter;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.View, View.OnClickListener {

    private final static String TAG = LoginActivity.class.getName();
    private static final int RC_GOOGLE_SIGN_IN = 9001;
//    private static final String FACEBOOK_EMAIL_PERMISSION_KEYWORD = "email";
    private static int RC_FACEBOOK_SIGN_IN;

    @Inject
    LoginPresenter loginPresenter;
    @Inject
    CallbackManager callbackManager;

    private ProfileTracker profileTracker;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, ">>>>>>>>>>>>>>>>> Login Activity onCreate() is running<<<<<<<<<<<<<<<<");

        initiateDagger();

        //Now the check and forward code are put in the Resume method.

        //Register the callback for login button
        registerFacebookLoginCallback();

        //Prepare Google login
        prepareGoogleLogin();

        //registerFacebookLogging();
    }

    /*
    *   The method that prepare the necessary class for Google Sign in function.
     */
    private void prepareGoogleLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, null /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        findViewById(R.id.google_login_button).setOnClickListener(this);

//        MyApplication.getApplication(this).setGoogleApiClient(googleApiClient);
    }

    private boolean isLogined() {
        // Check whether application has a user with id.
        User user = MyApplication.getApplication(this).getUser();
        return user != null && user.getId() != null && !user.getId().equals("");
    }

    /*
    * Method
    * Register the callback object for the Facebook login button
    */
    private void registerFacebookLoginCallback() {
        final Activity activity = this;
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile != null && MyApplication.getApplication(activity).getUser() == null &&
                        loginPresenter.isUnlocked()) {
//                    Login success. Save the name and id to the SharePreference and Application's User object.
//                    lock it when the first time profile changed.
                    loginPresenter.lock();
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
        //Get the request code for onActivityResult method
        RC_FACEBOOK_SIGN_IN = ((LoginButton) findViewById(R.id.facebook_login_button)).getRequestCode();
        /*Request reading email address permission
        List<String> additionalPermissionList = new LinkedList<>();
        additionalPermissionList.add(FACEBOOK_EMAIL_PERMISSION_KEYWORD);
        LoginManager.getInstance().logInWithReadPermissions(this, additionalPermissionList);
        */
    }

    /*
    *   Process Facebook and Google logging
    */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, ">>>>>>>>>>>  onActivityResult  <<<<<<<<<<<<");
        //Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_FACEBOOK_SIGN_IN) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == RC_GOOGLE_SIGN_IN) {
            handleGoogleSignInResult(data);
        }
    }

    private void handleGoogleSignInResult(Intent data) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        Log.d(TAG, ">>>>>>>>>>>>>>> Google sign in handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            //Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            if (acct != null) {
                Log.d(TAG, ">>>>>>>>>>>> Google id: " + acct.getId());
                Log.d(TAG, ">>>>>>>>>>>> Google name: " + acct.getDisplayName());
                Log.d(TAG, ">>>>>>>>>>>> Google email: " + acct.getEmail());
                Log.d(TAG, ">>>>>>>>>>>> Google account: " + acct.getAccount());
                if (loginPresenter.isUnlocked()){
                    loginPresenter.lock();
                    loginPresenter.saveGoogleUserInformation(acct.getId(), acct.getDisplayName(), acct.getEmail());
                }
                //Since we do not use back end code to check the token, the Google connect will be disconnect now.
                if (googleApiClient.isConnected()){
                    Auth.GoogleSignInApi.signOut(googleApiClient);
                    googleApiClient.disconnect();
                    //googleApiClient.connect();
                }
            }
        }
    }

    //Initiate dagger dependency injection
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
//      After save User to application, unlock the presenter.
        loginPresenter.unlock();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, ">>>>>>>>>>> Login Activity onResume() is running<<<<<<<<<<<<");
        super.onResume();
        // Before initialize everything, check whether the user has already logged in.
        // If so, forward to main screen directly without initialization
        if (isLogined()) showMainActivity();
        else loginPresenter.resume();
    }

    @Override
    public void hideProgress() {}

    @Override
    public void showProgress() {}

    @Override
    public void ShowError(String message) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (profileTracker != null) profileTracker.stopTracking();
    }

    /*
    *   Conduct when click Google Sign in button
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.google_login_button:
                signInGoogle();
                break;
        }
    }

    /*
    *   The method to deal with Google Sign In
     */
    private void signInGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
    }
}

