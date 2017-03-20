package com.pengwang.mybaby.domain.repository;

import com.pengwang.mybaby.domain.models.User;

/**
 * Created by Peng on 3/19/2017.
 * The interface for Database
 */

public interface DatabaseRepository {
    void saveFacebookUser(User user);
}
