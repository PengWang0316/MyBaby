package com.pengwang.mybaby.domain.interactors.impl;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.GetInitialRecordsInteractor;
import com.pengwang.mybaby.domain.interactors.base.AbstractInteractor;
import com.pengwang.mybaby.domain.models.Record;
import com.pengwang.mybaby.domain.repository.RecordRepository;

import java.util.List;

/**
 * Created by Peng on 2/12/2017.
 * The code for this interator
 */

public class GetInitialRecordsInteractorImpl extends AbstractInteractor implements GetInitialRecordsInteractor{
    private static final String THE_MESSAGE_OF_ILLEGAL_ARGUMENT = "The arguments can not be " +
            "null.";
    private RecordRepository mRecordRepository;
    private Callback mCallBack;

    public GetInitialRecordsInteractorImpl(Executor executor, MainThread mainThread, RecordRepository
            recordRepository, Callback callback){
        super(executor,mainThread);
        if (recordRepository==null || callback==null) throw new IllegalArgumentException(THE_MESSAGE_OF_ILLEGAL_ARGUMENT);
        mCallBack=callback;
        mRecordRepository=recordRepository;
    }

    @Override
    public void run() {
//        Get data from repository
        final List<Record> recordList=mRecordRepository.getInitialRecords();


//Run update view code in main thread
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallBack.onInitialRecordsRetrieved(recordList);
            }
        });


    }
}
