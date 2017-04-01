package com.pengwang.mybaby.network;

import android.support.annotation.NonNull;

import java.util.Map;

/**
 * Created by Peng on 3/24/2017.
 * The interface that uses to connect Restful services
 */

public interface RestConnection {
    String get(@NonNull String url);
    String post(@NonNull String url, @NonNull String bodyString);
}
