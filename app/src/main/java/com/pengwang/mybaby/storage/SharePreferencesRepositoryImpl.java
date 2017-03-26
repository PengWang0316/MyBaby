package com.pengwang.mybaby.storage;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.pengwang.mybaby.R;
import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.domain.repository.SharePreferencesRepository;

/**
 * Created by Peng on 2/24/2017.
 * Implement of Repository
 */
public class SharePreferencesRepositoryImpl implements SharePreferencesRepository {

    private final static String TAG = SharePreferencesRepositoryImpl.class.getName();
    private Activity activity;
    private SharedPreferences sharedPreferences;

    public SharePreferencesRepositoryImpl(Activity activity) {
        this.activity = activity;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    @Override
    public User getUser() {
        User user = new User();
        user.setName(sharedPreferences.getString(activity.getString(R.string.user_name_key), null));
        user.setId(sharedPreferences.getString(activity.getString(R.string.user_id_key), null));
        user.setFacebookId(sharedPreferences.getString(activity.getString(R.string.user_facebook_id_key),null));
        user.setGoogleId(sharedPreferences.getString(activity.getString(R.string.user_google_id_key),null));
        Log.d(TAG, ">>>>>>>>>>>>> user id: "+ user.getId());
        Log.d(TAG, ">>>>>>>>>>>>> user name: "+ user.getName());
        Log.d(TAG, ">>>>>>>>>>>>> user Facebook id: "+ user.getFacebookId());
        Log.d(TAG, ">>>>>>>>>>>>> user Google id: "+ user.getGoogleId());
        return user;
    }

    @Override
    public void saveUser(User user) {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(activity.getString(R.string.user_id_key),user.getId());
        editor.putString(activity.getString(R.string.user_name_key),user.getName());
        editor.putString(activity.getString(R.string.user_facebook_id_key),user.getFacebookId());
        editor.putString(activity.getString(R.string.user_google_id_key),user.getGoogleId());
        editor.apply();
    }

    /*
    *   Remove user information from shared preferences.
     */
    @Override
    public void removeUser() {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove(activity.getString(R.string.user_id_key));
        editor.remove(activity.getString(R.string.user_name_key));
        editor.remove(activity.getString(R.string.user_facebook_id_key));
        editor.remove(activity.getString(R.string.user_google_id_key));
        editor.apply();
    }
}
