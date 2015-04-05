package com.mobidream.mobred.iview;

import com.mobidream.mobred.iview.IBaseView;

/**
 * Created by SunilMishra on 4/2/2015.
 */
public interface ILoginView extends IBaseView {

    void setUsernameError();

    void setPasswordError();

    void showStatusView(String title, String message);
}
