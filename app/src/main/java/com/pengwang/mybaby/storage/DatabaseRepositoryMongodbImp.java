package com.pengwang.mybaby.storage;

import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.domain.repository.DatabaseRepository;
import com.pengwang.mybaby.network.RestConnection;

/**
 * Created by Peng on 3/19/2017.
 * For Mysql database Implementation
 */

public class DatabaseRepositoryMongodbImp implements DatabaseRepository {
//    private final static String TAG = DatabaseRepositoryMongodbImp.class.getName();
    private static final String DOMAIN_NAME = "https://Arthurbwang.com";
    private static final String API_VERSION = "/api/v1";
    private RestConnection restConnection;

    public DatabaseRepositoryMongodbImp(RestConnection restConnection) {
        this.restConnection = restConnection;
    }

    //   Query database to see whether this user has already existed
//   If so, get id for user object. If not, create a new user in the database and set the user id.
    @Override
    public void saveFacebookUser(User user) {
        user.setId(getUserIdFromFacebookId(user.getFacebookId()));
//        Log.d(TAG, ">>>>>>>>>>>>>>> User id was found from database : id = " + user.getId());
//      If the user does not exist in the database, create a new one and insert to the database.
        if (user.getId() == null || user.getId().equals("")) user.setId(createNewUser(user));
    }

    @Override
    public void saveGoogleUser(User user) {
        user.setId(getUserIdFromGoogleId(user.getGoogleId()));
        if (user.getId() == null || user.getId().equals("")) user.setId(createNewUser(user));
    }

    /*
    * Create a new user and insert it to the database.
    */
    private String createNewUser(User user) {
//        Log.d(TAG, ">>>>>>>>>>>>>>> createFacebookUser() create and insert a new user record to " +
//                "database<<<<<<<<<<<<<<<<");
//      Creating the params map and pass it as param
        return restConnection.post(DOMAIN_NAME + API_VERSION + "/users", user.getJsonString());
    }

    /*
    * Using Facebook id to fetch a user's id from the database.
    * Return null if can find this user from database.
     */
    private String getUserIdFromFacebookId(String facebookId) {
//        Log.d(TAG, ">>>>>>>>>>>>>>> getUserIdFromFacebookId() <<<<<<<<<<<<<<<<");

//      JSONObject jsonObject = new JSONObject(restConnection.get("http://34.208.114.192/users/facebookId="+facebookId));
//      return jsonObject.getString("_id");
        return restConnection.get(DOMAIN_NAME + API_VERSION + "/users?facebookId=" + (facebookId==null?"":facebookId));
    }


    /*
    * Using Google id to fetch a user's id from the database.
    * Return null if can find this user from database.
     */
    private String getUserIdFromGoogleId(String googleId) {
//        Log.d(TAG, ">>>>>>>>>>>>>>> getUserIdFromFacebookId() <<<<<<<<<<<<<<<<");

//      JSONObject jsonObject = new JSONObject(restConnection.get("http://34.208.114.192/users/facebookId="+facebookId));
//      return jsonObject.getString("_id");
        return restConnection.get(DOMAIN_NAME + API_VERSION + "/users?googleId=" + (googleId==null?"":googleId));

    }
}
