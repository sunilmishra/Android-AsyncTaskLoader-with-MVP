package com.mobidream.mobred.presenter;

import android.support.v4.app.FragmentActivity;

import com.mobidream.mobred.gateway.DataProvider;
import com.mobidream.mobred.gateway.RequestHolder;
import com.mobidream.mobred.iview.IBaseView;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

/**
 * Created by SunilMishra on 4/2/2015.
 * mishra1982@gmail.com
 */
public class BasePresenter<RESPONSE_TYPE> extends DataProvider<RESPONSE_TYPE> {

    private IBaseView baseView;
    private static final String BASE_URL = "http://192.168.1.5/test/";

    public BasePresenter(FragmentActivity context, IBaseView baseView) {
        super(context);
        this.baseView = baseView;
    }

    public void showProgressDialog(String message) {
        baseView.showProgressDialog(message);
    }

    public void hideProgressDialog() {
        baseView.hideProgressDialog();
    }

    protected void executeRequest(String url, Object postData, RESPONSE_TYPE responseType) {
        RequestHolder requestHolder = new RequestHolder();
        requestHolder.setUrl(BASE_URL + url);
        if (postData == null) {
            requestHolder.setMethodType(HttpGet.METHOD_NAME);
        } else {
            requestHolder.setMethodType(HttpPost.METHOD_NAME);
            requestHolder.setPostData(postData);
        }
        startLoader(requestHolder, responseType);
    }
}
