package com.tenblr.bhargav.tenblr.UI.Activities;

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
        boolean logout = false;

        if(getIntent()!=null)
            logout = getIntent().getBooleanExtra("logout",false);

        final boolean finalLogout = logout;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean loggedIn = new PrefUtil(SplashActivity.this).getBooleanPref(Constants.LOGGED_IN,false);
                if(loggedIn)
                {
                    Intent in = new Intent(SplashActivity.this,UserDashActivity.class);
                    in.putExtra("login",true);
                    startActivity(in);
                    finish();
                }
                else
                {
                    Intent in = new Intent(SplashActivity.this,OAuthLoginActivity.class);
                    in.putExtra("logout", finalLogout);
                    startActivity(in);
                    finish();
                }
            }
        },3000);
    }
}
