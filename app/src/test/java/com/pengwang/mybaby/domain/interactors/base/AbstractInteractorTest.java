package com.pengwang.mybaby.domain.interactors.base;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Peng on 2/22/2017.
 * Test for AbstractInteractor
 */
public class AbstractInteractorTest {
    private Executor executor;
    private MainThread mainThread;

    @Before
    public void setUp() throws Exception {
        executor = mock(Executor.class);
        mainThread = mock(MainThread.class);
    }

    @Test
    public void execute() throws Exception {
        AbstractInteractor interactor = new AbstractInteractor(executor, mainThread) {
            @Override
            public void run() {

            }
        };

        assertFalse(interactor.isRunning());
        assertFalse(interactor.isCanceled());

        interactor.execute();

        verify(executor).execute(interactor);
        assertTrue(interactor.isRunning());
        assertFalse(interactor.isCanceled());

        interactor.cancel();
        assertFalse(interactor.isRunning());
        assertTrue(interactor.isCanceled());

        interactor.onFinished();
        assertFalse(interactor.isRunning());
        assertFalse(interactor.isCanceled());


    }

}