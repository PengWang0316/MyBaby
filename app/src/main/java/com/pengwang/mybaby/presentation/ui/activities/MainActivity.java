package com.pengwang.mybaby.presentation.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.pengwang.mybaby.R;
import com.pengwang.mybaby.application.MyApplication;
import com.pengwang.mybaby.dagger.componets.DaggerApplicationComponent;
import com.pengwang.mybaby.dagger.componets.DaggerMainActivityComponent;
import com.pengwang.mybaby.dagger.componets.MainActivityComponent;
import com.pengwang.mybaby.dagger.modules.MainActivityModule;
import com.pengwang.mybaby.domain.executor.Impl.ThreadExecutor;
import com.pengwang.mybaby.domain.models.Record;
import com.pengwang.mybaby.presentation.presenters.MainPresenter;
import com.pengwang.mybaby.presentation.presenters.impl.MainPresenterImpl;
import com.pengwang.mybaby.storage.RecordRepositoryImpl;
import com.pengwang.mybaby.threading.MainThreadImpl;

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
        init();
    }

    private void init() {
        //        Initiate the main presenter
//        mMainpresentor = new MainPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this, new
//                RecordRepositoryImpl());

        //        Initiate the main presenter via dagger
        MainActivityComponent component = DaggerMainActivityComponent.builder().mainActivityModule(new
                MainActivityModule(this)).applicationComponent(MyApplication.getApplication(this)
                .getApplicationComponent()).build();
        component.injectMainActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.resume();
    }

    //The callback after get initial data method
    @Override
    public void showTheInitialData(List<Record> recordList) {
        TextView textView = (TextView) findViewById(R.id.main_activity_text);
        String allName = "";
        for (Record record : recordList) {
            allName += record.getName() + "  ";
        }
        textView.setText(allName);
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
