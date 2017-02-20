package com.pengwang.mybaby.dagger.modules;

import com.pengwang.mybaby.dagger.scopes.ApplicationScope;
import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.Impl.ThreadExecutor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Peng on 2/19/2017.
 * Initiate executor for dagger dependency
 */
@Module
public class ExecutorModule {
    @Provides
    @ApplicationScope
    public Executor executor(){
        return ThreadExecutor.getInstance();
    }
}
