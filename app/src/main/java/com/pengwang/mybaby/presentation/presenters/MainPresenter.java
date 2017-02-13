package com.pengwang.mybaby.presentation.presenters;

import com.pengwang.mybaby.domain.models.Record;
import com.pengwang.mybaby.presentation.ui.BaseView;

import java.util.List;

/**
 * Created by Peng on 2/12/2017.
 * This interface is for Main activity and defines its methods for view and interactors.
 */

public interface MainPresenter extends BasePresenter{
    interface View extends BaseView{
//        MainActivity View methods
        void showTheInitialData(List<Record> recordList);
    }
//    Business logic
    void getInitialData();
}
