package com.mobidream.mobred.view;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.mobidream.mobred.R;

/**
 * Created by SunilMishra on 22-08-2014.
 */
public class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureActionBar();
    }

    protected void configureActionBar() {
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
    }

    @Override
    public void setTitle(int titleId) {
        String title = getResources().getString(titleId);
        setTitle(title);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        getSupportActionBar().setTitle(title);
    }
}
