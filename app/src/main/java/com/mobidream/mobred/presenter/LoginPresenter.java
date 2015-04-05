package com.mobidream.mobred.presenter;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.mobidream.mobred.R;
import com.mobidream.mobred.gateway.DataProvider;
import com.mobidream.mobred.gateway.IDataProviderListener;
import com.mobidream.mobred.iview.ILoginView;
import com.mobidream.mobred.model.Login;
import com.mobidream.mobred.model.StatusMessage;

/**
 * Created by SunilMishra on 4/2/2015.
 * mishra1982@gmail.com
 */
public class LoginPresenter extends BasePresenter implements IDataProviderListener {

    private FragmentActivity context;
    private ILoginView loginView;

    public LoginPresenter(FragmentActivity context, ILoginView loginView) {
        super(context, loginView);
        this.context = context;
        this.loginView = loginView;
    }

    @SuppressWarnings("unchecked")
    public void validateCredentials(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            loginView.setUsernameError();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            loginView.setPasswordError();
            return;
        }
        addListener(this);
        Login login = new Login();
        login.setUserName(username);
        login.setPassword(password);
        executeRequest("login.php", login, StatusMessage.class);
    }

    @Override
    public void onActionPerformed(DataProvider dataProvider, Message message) {
        switch (message) {
            case DATA_REQUESTED:
                showProgressDialog(context.getString(R.string.loading));
                break;

            case DATA_RECEIVED:
                hideProgressDialog();
                StatusMessage statusMessage = (StatusMessage) getResponseData();
                loginView.showStatusView(statusMessage.getTitle(), statusMessage.getMessage());
                break;

            case DATA_FAILED:
                hideProgressDialog();
                break;
        }
    }

    public void onDestroy() {
        destroyLoader();
        removeListener(this);
    }
}