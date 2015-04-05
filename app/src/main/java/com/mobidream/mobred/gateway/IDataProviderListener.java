package com.mobidream.mobred.gateway;

import com.mobidream.mobred.gateway.DataProvider;

/**
 * Created by SunilMishra on 4/2/2015.
 * mishra1982@gmail.com
 */
public interface IDataProviderListener {

    static enum Message {
        DATA_REQUESTED,
        DATA_RECEIVED,
        DATA_FAILED;
    }

    void onActionPerformed(DataProvider dataProvider, Message message);
}
