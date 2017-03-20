package com.pengwang.mybaby.domain.interactors;

import com.pengwang.mybaby.domain.interactors.base.AbstractInteractor;
import com.pengwang.mybaby.domain.models.User;

/**
 * Created by Peng on 2/24/2017.
 * For get user name
 */
public interface GetUserInteractor extends Interactor{
    interface Callback{
        void onUserRetrieved(User user);
    }
}
