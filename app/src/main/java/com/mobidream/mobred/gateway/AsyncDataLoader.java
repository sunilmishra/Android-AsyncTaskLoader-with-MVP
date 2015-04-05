package com.mobidream.mobred.gateway;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by SunilMishra on 4/2/2015.
 * mishra1982@gmail.com
 */
public class AsyncDataLoader<DATA_MODEL> extends AsyncTaskLoader<DATA_MODEL> {

    private DATA_MODEL cachedData;
    private RequestHolder requestHolder;
    private DATA_MODEL responseType;

    public AsyncDataLoader(Context context, RequestHolder requestHolder, DATA_MODEL responseType) {
        super(context);
        this.requestHolder = requestHolder;
        this.responseType = responseType;
    }

    /**
     * Delivers the current response if present, otherwise
     * it starts loading (with its associated costs).
     */
    @Override
    protected void onStartLoading() {
        if (cachedData == null) {
            forceLoad();
        } else {
            super.deliverResult(cachedData);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public DATA_MODEL loadInBackground() {
        //Generic code to get data from network
        return (DATA_MODEL) new NetworkProvider(requestHolder, responseType).getResponseModel();
    }

    @Override
    public void deliverResult(DATA_MODEL newResponse) {
        if (!isReset()) {
            // Store the new response and deliver it if we are started.
            cachedData = newResponse;
            if (isStarted()) {
                // The superclass method deliver the results for us.
                super.deliverResult(newResponse);
            }
        }
    }

    /**
     * Ensures the loader has been stopped and drops the current response.
     */
    @Override
    protected void onReset() {
        onStopLoading();
        cachedData = null;
    }
}