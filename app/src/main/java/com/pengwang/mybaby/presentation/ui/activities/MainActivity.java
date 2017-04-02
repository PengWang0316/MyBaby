package com.pengwang.mybaby.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.pengwang.mybaby.R;
import com.pengwang.mybaby.application.MyApplication;
import com.pengwang.mybaby.dagger.componets.DaggerMainActivityComponent;
import com.pengwang.mybaby.dagger.componets.MainActivityComponent;
import com.pengwang.mybaby.dagger.modules.MainActivityModule;
import com.pengwang.mybaby.domain.models.Record;
import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.presentation.presenters.MainPresenter;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {
    private final static String TAG = MainActivity.class.getName();
    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        initiateDagger();
    }

    private void setupToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.tool_bar));
    }

    private void initiateDagger() {
        //Initiate the main presenter
        //mainPresenter = new MainPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this, new
        //RecordRepositoryImpl());

        //Initiate the main presenter via dagger
        MainActivityComponent component = DaggerMainActivityComponent.builder().mainActivityModule(new
                MainActivityModule(this,this)).applicationComponent(MyApplication.getApplication(this)
                .getApplicationComponent()).build();
        component.inject(this);
    }


    /*
    *   For menu actions.
    *   TODO extract menu as an Activity in order to let all activity reuse the code of action bar
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_menu_item:
                Log.d(TAG, ">>>>>>>>>>>>>>>>> Logout!! <<<<<<<<<<<<<");
                mainPresenter.logout(getUserFromApplication());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private User getUserFromApplication() {
        return MyApplication.getApplication(this).getUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.tool_bar_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.resume();
    }

    //The callback after get initial data method
    @Override
    public void showTheInitialData(List<Record> recordList) {
//        TextView textView = (TextView) findViewById(R.id.main_activity_text);
//        String allName = "";
//        for (Record record : recordList) {
//            allName += record.getName() + "  ";
//        }
//        textView.setText(allName);
    }

    /*
    *   Do not use back end check Token. So, did not save Google login status. Also do not have to log out from Google.
     */
    @Override
    public void showLoginActivity() {
//        1. Check if the user login with Facebook or Google.
//           also logout from there
//        2. Remove User object from application object.
        MyApplication myApplication = MyApplication.getApplication(this);
        User user = myApplication.getUser();
        if (user!=null && user.getFacebookId()!=null && !user.getFacebookId().equals("")) logoutFromFacebook();
//        else if (user!=null && user.getGoogleId()!=null && !user.getGoogleId().equals("")) logoutFromGoogle();
//        Do not really need to log out from Google since LoginActivity cleared up
        myApplication.setUser(null);
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void logoutFromFacebook() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken!=null && !accessToken.isExpired()){
            Log.d(TAG, ">>>>>>>> Log out from Facebook <<<<<<<<<  Token id: "+accessToken.getUserId());
            LoginManager.getInstance().logOut();
        }
    }

    @Override
    public void hideProgress() {
        Log.d(TAG, "---------Hide progress---------");
    }

    @Override
    public void showProgress() {
        Log.d(TAG, "---------Show progress---------");

    }

    @Override
    public void ShowError(String message) {

    }
}
