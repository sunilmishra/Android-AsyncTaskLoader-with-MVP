package com.mobidream.mobred.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobidream.mobred.R;
import com.mobidream.mobred.iview.ILoginView;
import com.mobidream.mobred.presenter.LoginPresenter;

/**
 * Created by SunilMishra on 4/2/2015.
 * mishra1982@gmail.com
 */
public class LoginFragment extends BaseFragment implements ILoginView {

    private LoginPresenter loginPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.loginPresenter = new LoginPresenter(getActivity(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        final EditText userNameEditText = (EditText) rootView.findViewById(R.id.et_login_username);
        final EditText passwordEditText = (EditText) rootView.findViewById(R.id.et_login_password);

        final Button loginButton = (Button) rootView.findViewById(R.id.btn_login_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.onDestroy(); //help to make request to server on each click.
                loginPresenter.validateCredentials(userNameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
        return rootView;
    }

    @Override
    public void setUsernameError() {
        Toast.makeText(getActivity(), R.string.username_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPasswordError() {
        Toast.makeText(getActivity(), R.string.password_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showStatusView(String title, String message) {
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder.setTitle(title);
        alertBuilder.setMessage(message);
        alertBuilder.setCancelable(true);
        alertBuilder.setPositiveButton(R.string.LABEL_OK, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alertBuilder.show();
    }
}
