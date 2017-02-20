package com.pengwang.mybaby.domain.interactors;

/**
 * Created by Peng on 2/12/2017.
 * Basic interactor interface that makes sure all interactor running in a backgournd thread.
 */

public interface Interactor {

    void execute();
    void cancel();
    void onFinished();
    void run();
}
