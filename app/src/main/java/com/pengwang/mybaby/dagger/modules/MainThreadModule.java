package com.pengwang.mybaby.dagger.modules;

import com.pengwang.mybaby.dagger.scopes.ApplicationScope;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.threading.MainThreadImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Peng on 2/19/2017.
 * Initiate mainthread class for dagger dependence
 */
@Module
public class MainThreadModule {
    @Provides
    @ApplicationScope
    public MainThread mainThread(){
        return MainThreadImpl.getInstance();
    }
}
