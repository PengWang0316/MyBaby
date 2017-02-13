package com.pengwang.mybaby.threading;

import android.os.Handler;
import android.os.Looper;

import com.pengwang.mybaby.domain.executor.MainThread;

/**
 * Created by Peng on 2/12/2017.
 *  Android main thread
 */

public class MainThreadImpl implements MainThread {
    private static MainThread mMainThread;
    private Handler mHandler;

//    Get handler with Android main thread
    private MainThreadImpl(){
        mHandler=new Handler(Looper.getMainLooper());
    }

    public static MainThread getInstance(){
        if (mMainThread==null) mMainThread=new MainThreadImpl();
        return mMainThread;
    }

//    Run the code in Android main thread via the Handler.
    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }
}
