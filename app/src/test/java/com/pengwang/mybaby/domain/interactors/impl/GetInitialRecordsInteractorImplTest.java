package com.pengwang.mybaby.domain.interactors.impl;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.GetInitialRecordsInteractor;
import com.pengwang.mybaby.domain.repository.RecordRepository;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Peng on 2/22/2017.
 *
 */
public class GetInitialRecordsInteractorImplTest {
    private GetInitialRecordsInteractor.Callback callback;
    private Executor executor;
    private MainThread mainThread;
    private RecordRepository recordRepository;

    @Before
    public void setup() {
        callback = mock(GetInitialRecordsInteractor.Callback.class);
        executor = mock(Executor.class);
        mainThread = mock(MainThread.class);
        recordRepository = mock(RecordRepository.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullRepositoryParameter() {
        new GetInitialRecordsInteractorImpl(executor, mainThread, null, callback);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullCallbackParameter() {
        new GetInitialRecordsInteractorImpl(executor, mainThread, recordRepository, null);
    }

    @Test
    public void run() throws Exception {
        GetInitialRecordsInteractor interactor = new GetInitialRecordsInteractorImpl(executor, mainThread,
                recordRepository,
                callback);
        interactor.run();

        verify(recordRepository).getInitialRecords();
    }

}