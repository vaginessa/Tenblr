package com.tenblr.bhargav.tenblr.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tenblr.bhargav.tenblr.R;
import com.tenblr.bhargav.tenblr.Utils.Constants;
import com.tenblr.bhargav.tenblr.Utils.PrefUtil;

/**
 * Created by bhargav on 15/11/16.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean loggedIn = new PrefUtil(SplashActivity.this).getBooleanPref(Constants.LOGGED_IN,false);
                if(loggedIn)
                {

                }
                else
                {
                    Intent in = new Intent(SplashActivity.this,OAuthLoginActivity.class);
                    startActivity(in);
                }
            }
        },3000);
    }
}
