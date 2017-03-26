package com.pengwang.mybaby.storage;

import android.util.Log;

import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.domain.repository.DatabaseRepository;
import com.pengwang.mybaby.network.RestConnection;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Peng on 3/19/2017.
 * For Mysql database Implementation
 */

public class DatabaseRepositoryMongodbImp implements DatabaseRepository {
    private final static String TAG = DatabaseRepositoryMongodbImp.class.getName();
    private RestConnection restConnection;

    public DatabaseRepositoryMongodbImp(RestConnection restConnection) {
        this.restConnection = restConnection;
    }

    //   Query database to see whether this user has already existed
//   If so, get id for user object. If not, create a new user in the database and set the user id.
    @Override
    public void saveFacebookUser(User user) {
        user.setId(getUserIdFromFacebookId(user.getFacebookId()));
        Log.d(TAG,">>>>>>>>>>>>>>> User id is "+user.getId());
//      If the user does not exist in the database, create a new one and insert to the database.
        if (user.getId() == null || user.getId().equals("")) user.setId(createFacebookUser());
    }

    /*
    * Create a new user and insert it to the database.
    */
    private String createFacebookUser() {
//        TODO create a user record in database and get the id back.
        Log.d(TAG, ">>>>>>>>>>>>>>> createFacebookUser() create and insert a user to database<<<<<<<<<<<<<<<<");
        return null;
    }

    /*
    * User facebook id to fetch user id from database.
    * Return null if can find this user from database.
     */
    private String getUserIdFromFacebookId(String facebookId) {
        Log.d(TAG, ">>>>>>>>>>>>>>> getUserIdFromFacebookId() <<<<<<<<<<<<<<<<");

//      JSONObject jsonObject = new JSONObject(restConnection.get("http://34.208.114.192/users/facebookId="+facebookId));
//      return jsonObject.getString("_id");
        return restConnection.get("http://34.208.114.192/api/users?facebookId=" + facebookId);
    }
}
