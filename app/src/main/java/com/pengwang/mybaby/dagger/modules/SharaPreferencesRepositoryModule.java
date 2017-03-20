package com.pengwang.mybaby.dagger.modules;

import android.app.Activity;

import com.pengwang.mybaby.domain.repository.SharePreferencesRepository;
import com.pengwang.mybaby.storage.SharePreferencesRepositoryImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Peng on 2/24/2017.
 * The module for shara preferences repository
 */
@Module
public class SharaPreferencesRepositoryModule {
    @Provides
    SharePreferencesRepository getSharaPreferencesRepository(Activity activity) {
        return new SharePreferencesRepositoryImpl(activity);
    }

}
