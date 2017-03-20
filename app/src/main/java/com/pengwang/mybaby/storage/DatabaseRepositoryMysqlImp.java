package com.pengwang.mybaby.storage;

import android.util.Log;

import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.domain.repository.DatabaseRepository;

/**
 * Created by Peng on 3/19/2017.
 * For Mysql database Implementation
 */

public class DatabaseRepositoryMysqlImp implements DatabaseRepository {
    private final static String TAG=DatabaseRepositoryMysqlImp.class.getName();

//   Query database to see whether this user has already existed
//   If so, get id for user object. If not, create a new user in the database and set the user id.
    @Override
    public void saveFacebookUser(User user) {
        user.setId(getUserIdFromFacebookId(user.getFacebookId()));
        if (user.getId() == null) user.setId(createFacebookUser());
    }

    private String createFacebookUser() {
//        TODO create a user record in database and get the id back.
        Log.d(TAG,">>>>>>>>>>>>>>> createFacebookUser() <<<<<<<<<<<<<<<<");
        return null;
    }

    private String getUserIdFromFacebookId(String facebookId) {
//        TODO fetch from database. Now it returns a fake id.
        Log.d(TAG,">>>>>>>>>>>>>>> getUserIdFromFacebookId() <<<<<<<<<<<<<<<<");
        return "123";
    }
}
