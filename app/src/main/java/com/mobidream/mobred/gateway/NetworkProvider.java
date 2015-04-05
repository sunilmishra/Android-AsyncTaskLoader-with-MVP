package com.mobidream.mobred.gateway;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by SunilMishra on 4/2/2015.
 * mishra1982@gmail.com
 */
public class NetworkProvider<RESPONSE_MODEL> {

    private Gson gson;
    private HttpClient httpClient;
    private Class<RESPONSE_MODEL> responseTypeClass;
    private RESPONSE_MODEL responseModel;

    @SuppressWarnings("unchecked")
    public NetworkProvider(RequestHolder requestHolder, RESPONSE_MODEL clazz) {
        this.gson = new Gson();
        httpClient = new DefaultHttpClient();
        responseTypeClass = (Class<RESPONSE_MODEL>) clazz;
        switch (requestHolder.getMethodType()) {
            case HttpGet.METHOD_NAME:
                responseModel = executeGetRequest(requestHolder);
                break;
            case HttpPost.METHOD_NAME:
                responseModel = executePostRequest(requestHolder);
                break;
        }
    }

    public RESPONSE_MODEL getResponseModel() {
        return responseModel;
    }

    public RESPONSE_MODEL executeGetRequest(RequestHolder requestHolder) {
        HttpGet httpGet = new HttpGet(requestHolder.getUrl());
        try {
            responseModel = responseData(httpClient.execute(httpGet));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseModel;
    }

    public RESPONSE_MODEL executePostRequest(RequestHolder requestHolder) {
        HttpPost httpPost = new HttpPost(requestHolder.getUrl());
        String postData = gson.toJson(requestHolder.getPostData());
        try {
            httpPost.setEntity(new StringEntity(postData));
            responseModel = responseData(httpClient.execute(httpPost));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseModel;
    }

    private RESPONSE_MODEL responseData(HttpResponse httpResponse) {
        try {
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(httpResponse.getEntity().getContent()));

                StringBuilder result = new StringBuilder();
                String response;
                while ((response = rd.readLine()) != null) {
                    result.append(response);
                }

                System.out.println("\n ===================== DATA FROM NETWORK ===============\n");
                System.out.println(result.toString() + "\n");
                System.out.println("\n ===================== END =============================\n");

                if (!result.toString().isEmpty()) {
                    responseModel = gson.fromJson(result.toString(), responseTypeClass);
                }
            }
        } catch (IOException e) { //TODO Handle applicable HTTP Status Code
            e.printStackTrace();
        }
        return responseModel;
    }
}
