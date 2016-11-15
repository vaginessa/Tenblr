package com.tenblr.bhargav.tenblr.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.tenblr.bhargav.tenblr.API.TumblrInterface;
import com.tenblr.bhargav.tenblr.API.TumblrService;
import com.tenblr.bhargav.tenblr.Adapters.BlogListAdapter;
import com.tenblr.bhargav.tenblr.Models.Blog;
import com.tenblr.bhargav.tenblr.Models.User;
import com.tenblr.bhargav.tenblr.Models.UserInfoResponse;
import com.tenblr.bhargav.tenblr.R;
import com.tenblr.bhargav.tenblr.Utils.PrefUtil;

import java.util.ArrayList;

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
    private TumblrInterface api;

    ArrayList<Blog> blogs = new ArrayList<>();
    BlogListAdapter adapter;
    User user = new User();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dash);
        pref = new PrefUtil(this);
        loginSuccess = getIntent().getBooleanExtra("login",false);
        api = TumblrService.getClient(UserDashActivity.this).create(TumblrInterface.class);
        if(!loginSuccess)
            Toast.makeText(this, "Login to Continue Using App", Toast.LENGTH_SHORT).show();
        else{
            getUserData();
        }

        initViews();
        bindViews();

        Call<Void> call = api.getBlogAvatar("bhargavav.tumblr.com");

      call.enqueue(new Callback<Void>() {
          @Override
          public void onResponse(Call<Void> call, Response<Void> response) {
              Log.v("Avatar",response.raw().request().url().toString());
          }

          @Override
          public void onFailure(Call<Void> call, Throwable t) {

          }
      });

    }



    public void getUserData()
    {
        Call<UserInfoResponse> call = api.getUserInfo();

        call.enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                user = response.body().getResponse().getUser();
                blogs = user.getBlogs();
                adapter.updateData(blogs);
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {

            }
        });
    }


    private void initViews() {
        rvBlogList = (RecyclerView) findViewById(R.id.rv_blog_list);
        layoutManager = new LinearLayoutManager(this);
        adapter = new BlogListAdapter(blogs);
    }

    private void bindViews() {
        rvBlogList.setLayoutManager(layoutManager);
        rvBlogList.setAdapter(adapter);

    }
}
