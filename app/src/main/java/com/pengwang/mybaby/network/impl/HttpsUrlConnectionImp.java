package com.pengwang.mybaby.network.impl;


import android.support.annotation.NonNull;
import android.util.Log;

import com.pengwang.mybaby.network.RestConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Peng on 3/29/2017.
 * The Https implementation of RestConnection class. Https connection.
 */

public class HttpsUrlConnectionImp implements RestConnection {

    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static final String ACCEPT_STRING = "Accept";
    private static final String APPLICATION_JSON = "application/json";
    private static final String TAG = HttpsUrlConnectionImp.class.getName();
    private static final String CONTENT_TYPE = "Content-Type";

    @Override
    public String get(@NonNull String httpsUrl) {
        Log.d(TAG, ">>>>>>>>Get URL is " + httpsUrl);
        String outputResponse = "";
        HttpsURLConnection connection = null;
        try {
            URL url = new URL(httpsUrl);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod(GET_METHOD);
            connection.setRequestProperty(ACCEPT_STRING, APPLICATION_JSON);

            outputResponse = getResponseFromConnection(connection.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.disconnect();
        }


        return outputResponse;
    }

    @Override
    public String post(@NonNull String httpsUrl, @NonNull String bodyString) {
        Log.d(TAG, ">>>>>>>>Post URL is " + httpsUrl);
        String outputResponse = "";
        HttpsURLConnection connection = null;
        try {
            URL url = new URL(httpsUrl);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod(POST_METHOD);
            connection.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            writeBodyParamsToConnection(bodyString, connection.getOutputStream());

//          Also want to get response from the server
            outputResponse = getResponseFromConnection(connection.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.disconnect();
        }


        return outputResponse;
    }

    /*
    * Write the params to connection
     */
    private void writeBodyParamsToConnection(String bodyString, OutputStream outputStream) throws
            IOException {
        Log.d(TAG, ">>>>>>>>>>>>>>The param body is: " + bodyString);
//        Start to write this url to connection.
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(bodyString);
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();
    }

    /*
    * Get the response content
     */
    private String getResponseFromConnection(InputStream inputStream) throws IOException {
        StringBuilder outputBuilder = new StringBuilder();
        String lineString;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while ((lineString = bufferedReader.readLine()) != null) outputBuilder.append(lineString);
        bufferedReader.close();
        inputStream.close();
        return outputBuilder.toString();
    }
}
