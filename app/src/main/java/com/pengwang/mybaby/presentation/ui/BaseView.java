package com.pengwang.mybaby.presentation.ui;

/**
 * Created by Peng on 2/12/2017.
 * This interface illustrates the methods that all view should have.
 */

public interface BaseView {
    //  Hide the progress bar or other indicator after background task
    void hideProgress();

    //  Show the progress bar or other indicator before background task
    void showProgress();

    //    Show the message for errors.
    void ShowError(String message);
}
