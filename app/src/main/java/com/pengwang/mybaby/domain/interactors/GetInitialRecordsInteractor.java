package com.pengwang.mybaby.domain.interactors;

import com.pengwang.mybaby.domain.models.Record;

import java.util.List;

/**
 * Created by Peng on 2/12/2017.
 * One of the interactor
 */

public interface GetInitialRecordsInteractor extends Interactor{
    interface Callback{
        void onInitialRecordsRetrieved(List<Record> recordList);
    }
}
