package com.mobidream.mobred.gateway;

import android.os.Bundle;

import java.util.HashSet;
import java.util.Set;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobidream.mobred.R;

/**
 * Created by SunilMishra on 4/2/2015.
 * mishra1982@gmail.com
 */
public abstract class DataProvider<RESPONSE_TYPE> implements LoaderManager.LoaderCallbacks<RESPONSE_TYPE> {

    private FragmentActivity context;
    private RequestHolder requestHolder;
    private RESPONSE_TYPE responseType;
    private final Set<IDataProviderListener> listeners;

    protected DataProvider(FragmentActivity context) {
        this.context = context;
        listeners = new HashSet<>();
    }

    protected final synchronized void addListener(IDataProviderListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    protected final void removeListener(IDataProviderListener listener) {
        if (listener != null) {
            listeners.remove(listener);
        }
    }

    @Override
    public Loader<RESPONSE_TYPE> onCreateLoader(int id, Bundle args) {
        for (IDataProviderListener listener : listeners) {
            listener.onActionPerformed(this, IDataProviderListener.Message.DATA_REQUESTED);
        }
        return new AsyncDataLoader<>(context, requestHolder, responseType);
    }

    @Override
    public void onLoadFinished(Loader<RESPONSE_TYPE> loader, RESPONSE_TYPE data) {
        responseType = data;
        if (responseType != null) { //TODO Need to be improved based on HTTP status code
            for (IDataProviderListener listener : listeners) {
                listener.onActionPerformed(this, IDataProviderListener.Message.DATA_RECEIVED);
            }
        } else {
            for (IDataProviderListener listener : listeners) {
                listener.onActionPerformed(this, IDataProviderListener.Message.DATA_FAILED);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<RESPONSE_TYPE> loader) {
        responseType = null;
    }

    protected void startLoader(RequestHolder requestHolder, RESPONSE_TYPE responseType) {
        this.requestHolder = requestHolder;
        this.responseType = responseType;
        context.getSupportLoaderManager().initLoader(R.id.loader_id, null, this);
    }

    protected RESPONSE_TYPE getResponseData() {
        return responseType;
    }

    //This method is used when you need to make request each time.
    //If you don't destroy Loader then it will return existing result.
    protected void destroyLoader() {
        context.getSupportLoaderManager().destroyLoader(R.id.loader_id);
    }
}
