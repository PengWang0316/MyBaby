package com.pengwang.mybaby.domain.repository;

import com.pengwang.mybaby.domain.models.Record;

import java.util.List;

/**
 * Created by Peng on 2/12/2017.
 * Defines the methods for record repository.
 */

public interface RecordRepository {
    List<Record> getInitialRecords();
}
