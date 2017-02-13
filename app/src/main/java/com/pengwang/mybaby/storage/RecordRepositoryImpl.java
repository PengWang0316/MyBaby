package com.pengwang.mybaby.storage;

import com.pengwang.mybaby.domain.models.Record;
import com.pengwang.mybaby.domain.repository.RecordRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Peng on 2/12/2017.
 *
 */

public class RecordRepositoryImpl implements RecordRepository {
//    Simulate getting date from database or preference
    @Override
    public List<Record> getInitialRecords() {
//        A converter may need to convert storage model to domain model.
//        Here just make it easy and just use domain model directly.
        List<Record> recordList=new LinkedList<>();
        Record r1=new Record();
        r1.setId("1");
        r1.setName("A");
        Record r2=new Record();
        r2.setId("2");
        r2.setName("B");
        Record r3=new Record();
        r3.setId("3");
        r3.setName("C");
        recordList.add(r1);
        recordList.add(r2);
        recordList.add(r3);

        return recordList;
    }
}
