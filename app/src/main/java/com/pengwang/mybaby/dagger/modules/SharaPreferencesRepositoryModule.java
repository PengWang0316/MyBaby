package com.pengwang.mybaby.dagger.modules;

import com.pengwang.mybaby.dagger.scopes.ApplicationScope;
import com.pengwang.mybaby.domain.repository.SharaPreferencesRepository;
import com.pengwang.mybaby.storage.SharaPreferencesRepositoryImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Peng on 2/24/2017.
 * The module for shara preferences repository
 */
@Module
public class SharaPreferencesRepositoryModule {
    @Provides
    @ApplicationScope
    SharaPreferencesRepository getSharaPreferencesRepository(){
        return new SharaPreferencesRepositoryImpl();
    }

}
