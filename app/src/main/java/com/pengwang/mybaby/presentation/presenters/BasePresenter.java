package com.pengwang.mybaby.presentation.presenters;

/**
 * Created by Peng on 2/12/2017.
 * This interface defines the basic life cycles that a presenter should control
 */

public interface BasePresenter {
    void resume();
void pause();
    void stop();
    void destroy();
    void onError(String message);
}
