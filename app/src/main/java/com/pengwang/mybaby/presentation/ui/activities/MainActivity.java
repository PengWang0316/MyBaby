package com.pengwang.mybaby.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
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
        //        Initiate the main presenter
//        mMainpresentor = new MainPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this, new
//                RecordRepositoryImpl());

        //        Initiate the main presenter via dagger
        MainActivityComponent component = DaggerMainActivityComponent.builder().mainActivityModule(new
                MainActivityModule(this,this)).applicationComponent(MyApplication.getApplication(this)
                .getApplicationComponent()).build();
        component.inject(this);
    }


    /*
    *   For menu actions.
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

    @Override
    public void showLoginActivity() {
//        Check if the user login with Facebook or Google.
//        also logout from there
        logoutFromFacebookAndGoogle();
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void logoutFromFacebookAndGoogle() {
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
