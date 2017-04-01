package com.pengwang.mybaby.network.impl;

import android.support.annotation.NonNull;

import com.pengwang.mybaby.network.RestConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Peng on 3/24/2017.
 * <p>
 * The implementation connect to Rest services via Java.net.HttpUrlConnection
 */

public class HttpUrlConnectionImp implements RestConnection {

    private static final String GET_METHOD = "GET";
    private static final String ACCEPT_STRING = "Accept";
    private static final String APPLICATION_JSON = "application/json";

    @Override
    public String get(@NonNull String httpUrl) {
        StringBuilder output=new StringBuilder();
        HttpURLConnection connection = null;
        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(GET_METHOD);
            connection.setRequestProperty(ACCEPT_STRING, APPLICATION_JSON);
//          If the connection did not receive http 200 response code, throw an exception.
            if (connection.getResponseCode() != 200)
                throw new RuntimeException("Failed to connect to " + url + " >>> The " +
                        "response code is " + connection.getResponseCode());
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String stringLine;
            while ((stringLine = reader.readLine()) != null) output.append(stringLine);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (connection!=null) connection.disconnect();
        }

        return output.toString();
    }

    @Override
    public String post(@NonNull String url, @NonNull String bodyString) {
        return null;
    }


}
