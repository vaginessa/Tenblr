package com.tenblr.bhargav.tenblr.UI.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.tenblr.bhargav.tenblr.API.TumblrInterface;
import com.tenblr.bhargav.tenblr.API.TumblrService;
import com.tenblr.bhargav.tenblr.Adapters.BlogListAdapter;
import com.tenblr.bhargav.tenblr.Communicators.PostDashCommunicator;
import com.tenblr.bhargav.tenblr.Model.DeleteResponse;
import com.tenblr.bhargav.tenblr.Model.UserInfo.Blog;
import com.tenblr.bhargav.tenblr.Model.UserInfo.User;
import com.tenblr.bhargav.tenblr.R;
import com.tenblr.bhargav.tenblr.UI.Fragments.BlogListFragment;
import com.tenblr.bhargav.tenblr.UI.Fragments.PostListFragment;
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
    private boolean deleted = false;
    PostDashCommunicator comm;
    Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dash);
        pref = new PrefUtil(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar_user_dash);
        setSupportActionBar(toolbar);
        loginSuccess = getIntent().getBooleanExtra("login",false);

        if(!loginSuccess)
            Toast.makeText(this, "Login to Continue Using App", Toast.LENGTH_SHORT).show();
        else{
            loadBlogList();
        }

        api = TumblrService.getClient(UserDashActivity.this).create(TumblrInterface.class);

        /*     Call<Void> call = api.getBlogAvatar("bhargavav.tumblr.com");

      call.enqueue(new Callback<Void>() {
          @Override
          public void onResponse(Call<Void> call, Response<Void> response) {
              Log.v("Avatar",response.raw().request().url().toString());
          }

          @Override
          public void onFailure(Call<Void> call, Throwable t) {

          }
      });*/

    }

    public void loadBlogList()
    {
        setFragmentLayout(new BlogListFragment(),"BlogList");
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
