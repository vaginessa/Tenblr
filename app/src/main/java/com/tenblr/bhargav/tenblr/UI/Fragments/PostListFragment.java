package com.tenblr.bhargav.tenblr.UI.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tenblr.bhargav.tenblr.API.TumblrInterface;
import com.tenblr.bhargav.tenblr.API.TumblrService;
import com.tenblr.bhargav.tenblr.Adapters.PostListAdapter;
import com.tenblr.bhargav.tenblr.Communicators.PostDashCommunicator;
import com.tenblr.bhargav.tenblr.Model.BlogInfo.BlogInfoResponse;
import com.tenblr.bhargav.tenblr.Model.BlogInfo.Post;
import com.tenblr.bhargav.tenblr.R;
import com.tenblr.bhargav.tenblr.UI.Activities.NewPostActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bhargav on 16/11/16.
 */

public class PostListFragment extends Fragment implements PostDashCommunicator {

    View rootView;
    RecyclerView rvPostList;
    LinearLayoutManager layoutManager;
    String blogName="";
    TumblrInterface api;
    PostListAdapter adapter;
    ArrayList<Post> posts = new ArrayList<>();
    boolean deleted = false;
    FloatingActionButton addPost;
    LinearLayout emptyView;



    public static PostListFragment newInstance(String blogName)
    {
        PostListFragment fragment = new PostListFragment();
        Bundle args = new Bundle();
        args.putString("blog",blogName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = TumblrService.getClient(getActivity()).create(TumblrInterface.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_post_list,container,false);
        if(getArguments()!=null)
            blogName = getArguments().getString("blog");
        initViews();
        bindViews();
        getPostData(blogName);
        return rootView;
    }

    public void getPostData(String blogName)
    {
        Call<BlogInfoResponse> call = api.getPosts(blogName+".tumblr.com","text","text");

        call.enqueue(new Callback<BlogInfoResponse>() {
            @Override
            public void onResponse(Call<BlogInfoResponse> call, Response<BlogInfoResponse> response) {
                posts = response.body().getResponse().getPosts();
                adapter.updateData(posts);
                if(posts.size()==0){
                    emptyView.setVisibility(View.VISIBLE);
                    rvPostList.setVisibility(View.GONE);
                }
                else{
                    emptyView.setVisibility(View.GONE);
                    rvPostList.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<BlogInfoResponse> call, Throwable t) {
                emptyView.setVisibility(View.VISIBLE);
                rvPostList.setVisibility(View.GONE);
                Toast.makeText(getActivity(),"Error fetching Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        rvPostList = (RecyclerView) rootView.findViewById(R.id.rv_post_list);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new PostListAdapter(posts,getActivity());
        addPost = (FloatingActionButton) rootView.findViewById(R.id.fab_add_post);
        emptyView = (LinearLayout) rootView.findViewById(R.id.post_empty);
    }

    private void bindViews() {
        rvPostList.setLayoutManager(layoutManager);
        rvPostList.setAdapter(adapter);
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), NewPostActivity.class);
                in.putExtra("blog",blogName);
                in.putExtra("edit",false);
                startActivity(in);
            }
        });
    }


    @Override
    public void postDelete(boolean deleted) {
        adapter.deletePost(deleted);
    }
}
