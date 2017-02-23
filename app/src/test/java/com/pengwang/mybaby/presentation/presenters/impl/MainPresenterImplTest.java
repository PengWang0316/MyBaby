package com.pengwang.mybaby.presentation.presenters.impl;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.GetInitialRecordsInteractor;
import com.pengwang.mybaby.domain.models.Record;
import com.pengwang.mybaby.presentation.presenters.MainPresenter;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Peng on 2/22/2017.
 * Test for MainPresenterImp class
 */
public class MainPresenterImplTest {

    private Executor executor;
    private MainThread mainThread;
//    private RecordRepository recordRepository;
    private GetInitialRecordsInteractor getInitialRecordsInteractor;
    private MainPresenter.View view;

    @Before
    public void setUp() throws Exception {
        executor = mock(Executor.class);
        mainThread = mock(MainThread.class);
//        recordRepository = mock(RecordRepository.class);
        getInitialRecordsInteractor = mock(GetInitialRecordsInteractor.class);
        view = mock(MainPresenter.View.class);
    }

    @Test
    public void getInitialData() throws Exception {
        MainPresenterImpl mainPresenter = new MainPresenterImpl(executor, mainThread,view);
        mainPresenter.setGetInitialRecordsInteractor(getInitialRecordsInteractor);

        mainPresenter.getInitialData();

        verify(view).showProgress();
        verify(getInitialRecordsInteractor).execute();

    }

    @Test
    public void onInitialRecordsRetrieved() throws Exception {
        MainPresenterImpl mainPresenter = new MainPresenterImpl(executor, mainThread,view);
        mainPresenter.setGetInitialRecordsInteractor(getInitialRecordsInteractor);
        List<Record> list=new LinkedList<>();
        mainPresenter.onInitialRecordsRetrieved(list);

        verify(view).showTheInitialData(list);
        verify(view).hideProgress();
    }

    @Test
    public void resume() throws Exception{
        MainPresenterImpl mainPresenter = new MainPresenterImpl(executor, mainThread,view);
        mainPresenter.setGetInitialRecordsInteractor(getInitialRecordsInteractor);

        mainPresenter.resume();

        verify(view).showProgress();
        verify(getInitialRecordsInteractor).execute();
    }

}