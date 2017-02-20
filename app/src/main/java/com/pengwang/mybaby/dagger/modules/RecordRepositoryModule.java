package com.pengwang.mybaby.dagger.modules;

import com.pengwang.mybaby.domain.repository.RecordRepository;
import com.pengwang.mybaby.storage.RecordRepositoryImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Peng on 2/19/2017.
 *
 */
@Module
public class RecordRepositoryModule {
//    Not singleton
    @Provides
    RecordRepository getRecordRepository(){
        return new RecordRepositoryImpl();
    }
}
