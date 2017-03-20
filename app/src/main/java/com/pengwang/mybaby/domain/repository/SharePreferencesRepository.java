package com.pengwang.mybaby.domain.repository;

import com.pengwang.mybaby.domain.models.User;

/**
 * Created by Peng on 2/24/2017.
 * For checking login status.
 * return a username if has already login
 */

public interface SharePreferencesRepository {
    User getUser();
    void saveUser(User user);
}
