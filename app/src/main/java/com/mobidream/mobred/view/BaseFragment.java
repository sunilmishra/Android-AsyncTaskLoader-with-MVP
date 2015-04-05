package com.mobidream.mobred.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mobidream.mobred.iview.IBaseView;

/**
 * Created by SunilMishra on 4/3/2015.
 * mishra1982@gmail.com
 */
public class BaseFragment extends Fragment implements IBaseView {

    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(getActivity());
    }

    @Override
    public void showProgressDialog(String message) {
        if (progressDialog.isShowing()) {
            hideProgressDialog();
        }
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.hide();
    }
}
