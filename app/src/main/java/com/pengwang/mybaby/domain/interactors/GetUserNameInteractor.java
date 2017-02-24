package com.pengwang.mybaby.domain.interactors;

import com.pengwang.mybaby.domain.interactors.base.AbstractInteractor;

/**
 * Created by Peng on 2/24/2017.
 * For get user name
 */
public interface GetUserNameInteractor extends Interactor{
    interface Callback{
        void onUsernameRetrieved(String username);
    }
}
