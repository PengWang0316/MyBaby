package com.pengwang.mybaby.presentation.presenters;

import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.presentation.ui.BaseView;

/**
 * Created by Peng on 2/24/2017.
 * The interface for login presenter
 */
public interface LoginPresenter extends BasePresenter {

    interface View extends BaseView{
        void showMainActivity();
        void setUserToApplication(User user);
    }

    void checkLoginStatus();
    void saveFacebookUserInformation(String facebookId, String facebookName);
//  Using a lock to prevent run the loginPresenter.saveFacebookUserInformation several times due to facebook's
//  profile will be changed several times during the login.
    boolean isUnlocked();
    void lock();
    void unlock();
}
