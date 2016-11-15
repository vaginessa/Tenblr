package com.tenblr.bhargav.tenblr.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tenblr.bhargav.tenblr.API.SampleModel;
import com.tenblr.bhargav.tenblr.API.TumblrInterface;
import com.tenblr.bhargav.tenblr.API.TumblrService;
import com.tenblr.bhargav.tenblr.R;
import com.tenblr.bhargav.tenblr.Utils.PrefUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bhargav on 15/11/16.
 */

public class UserDashActivity extends AppCompatActivity {

    RecyclerView rvBlogList;
    LinearLayoutManager layoutManager;

    boolean loginSuccess;

    PrefUtil pref;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dash);
        pref = new PrefUtil(this);
        loginSuccess = getIntent().getBooleanExtra("login",false);
        if(!loginSuccess)
            Toast.makeText(this, "Login to Continue Using App", Toast.LENGTH_SHORT).show();
        initViews();
        bindViews();


        TumblrInterface api = TumblrService.getClient(UserDashActivity.this).create(TumblrInterface.class);

        Call<SampleModel> call = api.getTopRatedMovies("bhargavav.tumblr.com");

        call.enqueue(new Callback<SampleModel>() {
            @Override
            public void onResponse(Call<SampleModel> call, Response<SampleModel> response) {
                Toast.makeText(UserDashActivity.this, "Successs !!!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SampleModel> call, Throwable t) {

            }
        });




    }


    private void initViews() {
        rvBlogList = (RecyclerView) findViewById(R.id.rv_blog_list);
        layoutManager = new LinearLayoutManager(this);
    }

    private void bindViews() {
        rvBlogList.setLayoutManager(layoutManager);

    }
}
