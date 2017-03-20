package com.pengwang.mybaby.storage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.pengwang.mybaby.R;
import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.domain.repository.SharePreferencesRepository;

/**
 * Created by Peng on 2/24/2017.
 * Implement of Repository
 */
public class SharePreferencesRepositoryImpl implements SharePreferencesRepository {
    //    TODO change this method to use shara preferences
    private Activity activity;
    private SharedPreferences sharedPreferences;

    public SharePreferencesRepositoryImpl(Activity activity) {
        this.activity = activity;
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    @Override
    public User getUser() {
        User user = new User();
        user.setName(sharedPreferences.getString(activity.getString(R.string.user_name_key), null));
        user.setId(sharedPreferences.getString(activity.getString(R.string.user_id_key), null));
        return user;
    }

    @Override
    public void saveUser(User user) {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(activity.getString(R.string.user_id_key),user.getId());
        editor.putString(activity.getString(R.string.user_name_key),user.getName());
        editor.apply();
    }
}
