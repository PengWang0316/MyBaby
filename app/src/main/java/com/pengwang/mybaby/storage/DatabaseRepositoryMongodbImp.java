package com.pengwang.mybaby.storage;

import android.util.Log;

import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.domain.repository.DatabaseRepository;

/**
 * Created by Peng on 3/19/2017.
 * For Mysql database Implementation
 */

public class DatabaseRepositoryMongodbImp implements DatabaseRepository {
    private final static String TAG=DatabaseRepositoryMongodbImp.class.getName();

//   Query database to see whether this user has already existed
//   If so, get id for user object. If not, create a new user in the database and set the user id.
    @Override
    public void saveFacebookUser(User user) {
        user.setId(getUserIdFromFacebookId(user.getFacebookId()));
//      If the user does not exist in the database, create a new one and insert to the database.
        if (user.getId() == null) user.setId(createFacebookUser());
    }

    /*
    * Create a new user and insert it to the database.
    */
    private String createFacebookUser() {
//        TODO create a user record in database and get the id back.
        Log.d(TAG,">>>>>>>>>>>>>>> createFacebookUser() create and insert a user to database<<<<<<<<<<<<<<<<");
        return null;
    }

    /*
    * User facebook id to fetch user id from database.
    * Return null if can find this user from database.
     */
    private String getUserIdFromFacebookId(String facebookId) {
//        TODO fetch from database. Now it returns a fake id.
        Log.d(TAG,">>>>>>>>>>>>>>> getUserIdFromFacebookId() <<<<<<<<<<<<<<<<");
        return "123";
    }
}
