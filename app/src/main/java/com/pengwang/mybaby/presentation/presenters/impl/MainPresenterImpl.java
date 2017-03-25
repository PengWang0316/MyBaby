package com.pengwang.mybaby.presentation.presenters.impl;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.GetInitialRecordsInteractor;
import com.pengwang.mybaby.domain.interactors.LogoutInteractor;
import com.pengwang.mybaby.domain.models.Record;
import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.presentation.presenters.MainPresenter;

import java.util.List;

/**
 * Created by Peng on 2/12/2017.
 * Main presenter implement.
 */

public class MainPresenterImpl extends AbstractPresenter implements MainPresenter, GetInitialRecordsInteractor
        .Callback, LogoutInteractor.Callback {

    private MainPresenter.View view;
//    private RecordRepository recordRepository;
    private GetInitialRecordsInteractor getInitialRecordsInteractor;
    private LogoutInteractor logoutInteractor;

    public MainPresenterImpl(Executor executor, MainThread mainThread, View view) {
        super(executor, mainThread);
//        Save the view and main thread
        this.view = view;
//        this.recordRepository = recordRepository;

    }

    //  Call interactor to get the result.
    @Override
    public void getInitialData() {
        showProgress();
//        use dagger set.
//        GetInitialRecordsInteractor interactor = new GetInitialRecordsInteractorImpl(mExecutor, mMainThread,
//                recordRepository, this);
        getInitialRecordsInteractor.execute();
    }

    /*
    * Use to logout from the system
     */
    @Override
    public void logout(User userFromApplication) {
        showProgress();
        logoutInteractor.execute();
    }

    /*
    *  Callback from LogoutInteractor
     */
    @Override
    public void onLogout() {
        view.showLoginActivity();
        hideProgress();
    }

    private void hideProgress() {
        view.hideProgress();
    }

    private void showProgress() {
        view.showProgress();
    }

    /*
    *   Callback from GetInitialRecordsInteractor
    *   After interactor get the result, this callback will be called to update view
     */

    @Override
    public void onInitialRecordsRetrieved(List<Record> recordList) {
        view.showTheInitialData(recordList);
        hideProgress();
    }

    @Override
    public void resume() {
        getInitialData();
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

//use to test
    public void setGetInitialRecordsInteractor(GetInitialRecordsInteractor getInitialRecordsInteractor) {
        this.getInitialRecordsInteractor = getInitialRecordsInteractor;
    }

    public void setLogoutInteractor(LogoutInteractor logoutInteractor) {
        this.logoutInteractor = logoutInteractor;
    }


}
