package com.pengwang.mybaby.dagger.modules;

import com.pengwang.mybaby.domain.repository.DatabaseRepository;
import com.pengwang.mybaby.network.RestConnection;
import com.pengwang.mybaby.network.impl.HttpUrlConnectionImp;
import com.pengwang.mybaby.storage.DatabaseRepositoryMongodbImp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Peng on 3/19/2017.
 * Database Repository module
 */
@Module
public class DatabaseRepositoryModule {
    @Provides
    DatabaseRepository getDatabaseRepository(RestConnection restConnection){
        return new DatabaseRepositoryMongodbImp(restConnection);
    }

    @Provides
    RestConnection getRestConnection(){
        return new HttpUrlConnectionImp();
    }
}
