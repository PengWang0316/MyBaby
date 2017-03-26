package com.pengwang.mybaby.network;

import android.support.annotation.NonNull;

/**
 * Created by Peng on 3/24/2017.
 * The interface that uses to connect Restful services
 */

public interface RestConnection {
    String get(@NonNull String Url);
}
