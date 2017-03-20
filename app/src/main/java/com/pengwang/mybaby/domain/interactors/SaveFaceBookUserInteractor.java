package com.pengwang.mybaby.domain.interactors;

import com.pengwang.mybaby.domain.models.User;

/**
 * Created by Peng on 3/19/2017.
 * Save Facebook user to the Repository
 */

public interface SaveFaceBookUserInteractor extends Interactor {
    //No callback needed
//    There is nothing to do after saving.
    interface Callback {
        void onSaveFaceBookUser(User user);
    }
    void setFacebookUserId(String facebookUserId);

    void setFacebookUserName(String facebookUserName);

}
