package com.pengwang.mybaby.domain.executor.Impl;

import com.pengwang.mybaby.domain.executor.Executor;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Peng on 2/19/2017.
 *
 */
public class ThreadExecutorTest {


    @Test
    public void execute() throws Exception {
        Executor executor1=ThreadExecutor.getInstance();
        Executor executor2=ThreadExecutor.getInstance();
        assertEquals(executor1,executor2);
    }

    @Test
    public void getInstance() throws Exception {

    }

}