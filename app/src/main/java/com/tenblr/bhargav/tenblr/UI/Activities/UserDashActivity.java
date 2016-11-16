package com.tenblr.bhargav.tenblr.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tenblr.bhargav.tenblr.API.TumblrInterface;
import com.tenblr.bhargav.tenblr.API.TumblrService;
import com.tenblr.bhargav.tenblr.Communicators.PostDashCommunicator;
import com.tenblr.bhargav.tenblr.Model.DeleteResponse;
import com.tenblr.bhargav.tenblr.R;
import com.tenblr.bhargav.tenblr.UI.Fragments.BlogListFragment;
import com.tenblr.bhargav.tenblr.UI.Fragments.PostListFragment;
import com.tenblr.bhargav.tenblr.Utils.PrefUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bhargav on 15/11/16.
 */

public class UserDashActivity extends AppCompatActivity {

    boolean loginSuccess;
    PrefUtil pref;
    private TumblrInterface api;
    PostDashCommunicator comm;
    Toolbar toolbar;
    LinearLayout viewLogin;
    Button btnLogin;
    FrameLayout blogView;

    ImageView ivLogout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dash);
        pref = new PrefUtil(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar_user_dash);
        setSupportActionBar(toolbar);
        loginSuccess = getIntent().getBooleanExtra("login",false);
        blogView = (FrameLayout) findViewById(R.id.dash_frag_container);
        viewLogin = (LinearLayout) findViewById(R.id.view_login_cont);
        btnLogin = (Button) viewLogin.findViewById(R.id.btn_login);
        ivLogout = (ImageView) toolbar.findViewById(R.id.iv_logout);
        if(!loginSuccess){
            getSupportActionBar().setTitle("Login to Continue");
            viewLogin.setVisibility(View.VISIBLE);
            blogView.setVisibility(View.GONE);
            ivLogout.setVisibility(View.GONE);
        }
        else{
            loadBlogList();
            viewLogin.setVisibility(View.GONE);
            blogView.setVisibility(View.VISIBLE);
            ivLogout.setVisibility(View.VISIBLE);
            api = TumblrService.getClient(UserDashActivity.this).create(TumblrInterface.class);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(UserDashActivity.this,OAuthLoginActivity.class);
                startActivity(in);
                finish();
            }
        });

        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref.clearAllSharedPreferences();
                Intent in = new Intent(UserDashActivity.this,SplashActivity.class);
                in.putExtra("logout",true);
                startActivity(in);
            }
        });
    }


    public void loadBlogList()
    {
        getSupportFragmentManager().beginTransaction().add(R.id.dash_frag_container,new BlogListFragment()).commit();
    }

    public void loadPostList(String blogName)
    {
        PostListFragment frag = PostListFragment.newInstance(blogName);
        comm = (PostDashCommunicator) frag;
        setFragmentLayout(frag,"PostList");
    }

    private void setFragmentLayout(Fragment fragmentName, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        if (fragmentName.isAdded()) {
            fragmentTransaction.remove(fragmentName);
        }
        fragmentTransaction.replace(R.id.dash_frag_container, fragmentName, tag).addToBackStack(tag);
        fragmentTransaction.commit();

    }

    public void deletePost(String blogName,String postId)
    {
        Call<DeleteResponse> call = api.deletePost(blogName,postId);
        call.enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                if(response.body().getMeta().getStatus()==200)
                    comm.postDelete(true);
                else
                    comm.postDelete(false);
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                comm.postDelete(false);
            }
        });
    }

}
