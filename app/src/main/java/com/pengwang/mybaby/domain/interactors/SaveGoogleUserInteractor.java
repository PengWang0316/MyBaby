package com.pengwang.mybaby.domain.interactors;

import com.pengwang.mybaby.domain.models.User;

/**
 * Created by Peng on 4/1/2017.
 * The interface for SaveGoogleUserInteractor
 */

public interface SaveGoogleUserInteractor extends Interactor {
    interface Callback{
        void onSaveGoogleUser (User user);
    }

    void setGoogleId(String googleId);
    void setGoogleName(String googleName);
    void setGoogleEmail(String googleEmail);
}
