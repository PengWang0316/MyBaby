package com.pengwang.mybaby.domain.interactors;

/**
 * Created by Peng on 3/24/2017.
 * For log out action
 */

public interface LogoutInteractor extends Interactor{
    interface Callback{
        void onLogout();
    }
}
