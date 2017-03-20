package com.pengwang.mybaby.dagger.componets;

import com.pengwang.mybaby.application.MyApplication;
import com.pengwang.mybaby.dagger.modules.ExecutorModule;
import com.pengwang.mybaby.dagger.modules.MainThreadModule;
import com.pengwang.mybaby.dagger.scopes.ApplicationScope;
import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;

import dagger.Component;

/**
 * Created by Peng on 2/19/2017.
 * For application component
 */
@Component (modules = {ExecutorModule.class, MainThreadModule.class})
@ApplicationScope
public interface ApplicationComponent {
    Executor getExecutor();
    MainThread getMainThread();
    void injectMyApplication(MyApplication myApplication);
}
