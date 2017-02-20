package com.pengwang.mybaby.presentation.presenters.impl;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.GetInitialRecordsInteractor;
import com.pengwang.mybaby.domain.interactors.impl.GetInitialRecordsInteractorImpl;
import com.pengwang.mybaby.domain.models.Record;
import com.pengwang.mybaby.domain.repository.RecordRepository;
import com.pengwang.mybaby.presentation.presenters.MainPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Peng on 2/12/2017.
 * Main presenter implement.
 */

public class MainPresenterImpl extends AbstractPresenter implements MainPresenter, GetInitialRecordsInteractor.Callback {

    private MainPresenter.View mView;
    private RecordRepository mRecordRepository;

    public MainPresenterImpl(Executor executor, MainThread mainThread, View view, RecordRepository recordRepository) {
        super(executor, mainThread);
//        Save the view and main thread
        mView = view;
        mRecordRepository = recordRepository;
    }

    //  Call interactor to get the result.
    @Override
    public void getInitialData() {
        mView.showProgress();
        GetInitialRecordsInteractor interactor = new GetInitialRecordsInteractorImpl(mExecutor, mMainThread,
                mRecordRepository, this);
        interactor.execute();
    }

    //    After interactor get the result, this callback will be called to update view
    @Override
    public void onInitialRecordsRetrieved(List<Record> recordList) {

        mView.showTheInitialData(recordList);
        mView.hideProgress();
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


}
