package com.tenblr.bhargav.tenblr.UI.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tenblr.bhargav.tenblr.API.TumblrInterface;
import com.tenblr.bhargav.tenblr.API.TumblrService;
import com.tenblr.bhargav.tenblr.Adapters.BlogListAdapter;
import com.tenblr.bhargav.tenblr.Model.UserInfo.UserInfoResponse;
import com.tenblr.bhargav.tenblr.Model.UserInfo.Blog;
import com.tenblr.bhargav.tenblr.Model.UserInfo.User;
import com.tenblr.bhargav.tenblr.R;
import com.tenblr.bhargav.tenblr.UI.Activities.UserDashActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bhargav on 16/11/16.
 */

public class BlogListFragment extends Fragment {

    View rootView;
    RecyclerView rvBlogList;
    LinearLayoutManager layoutManager;
    private TumblrInterface api;
    ArrayList<Blog> blogs = new ArrayList<>();
    BlogListAdapter adapter;
    User user = new User();
    TextView welcomeMessage;
    LinearLayout emptyView;
    TextView toolbarTitle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = TumblrService.getClient(getActivity()).create(TumblrInterface.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_blog_list,container,false);
        toolbarTitle = (TextView) ((UserDashActivity)getActivity()).findViewById(R.id.toolbar_title);
        toolbarTitle.setText("My Blogs");
        initViews();
        bindViews();
        getUserData();
        return rootView;
    }

    public void getUserData()
    {
        Call<UserInfoResponse> call = api.getUserInfo();

        call.enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                if(response.code()==200) {
                    user = response.body().getResponse().getUser();
                    blogs = user.getBlogs();
                    adapter.updateData(blogs);
                    welcomeMessage.setText("Welcome to Tenblr \n"+user.getName());

                    if(blogs.size()==0){
                        emptyView.setVisibility(View.VISIBLE);
                        rvBlogList.setVisibility(View.GONE);
                    }
                    else{
                        emptyView.setVisibility(View.GONE);
                        rvBlogList.setVisibility(View.VISIBLE);
                    }
                }


            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                emptyView.setVisibility(View.VISIBLE);
                rvBlogList.setVisibility(View.GONE);
                Toast.makeText(getActivity(),"Error fetching Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        rvBlogList = (RecyclerView) rootView.findViewById(R.id.rv_blog_list);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new BlogListAdapter(blogs,getActivity());
        welcomeMessage = (TextView) rootView.findViewById(R.id.welcome_message);
        emptyView = (LinearLayout) rootView.findViewById(R.id.blog_empty);
    }

    private void bindViews() {
        rvBlogList.setLayoutManager(layoutManager);
        rvBlogList.setAdapter(adapter);

    }


}
