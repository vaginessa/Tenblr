package com.tenblr.bhargav.tenblr.UI.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tenblr.bhargav.tenblr.API.TumblrInterface;
import com.tenblr.bhargav.tenblr.API.TumblrService;
import com.tenblr.bhargav.tenblr.Communicators.PostStateCommunicator;
import com.tenblr.bhargav.tenblr.Model.DeleteResponse;
import com.tenblr.bhargav.tenblr.R;
import com.tenblr.bhargav.tenblr.UI.Fragments.PostStateSheetFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bhargav on 16/11/16.
 */

public class NewPostActivity extends AppCompatActivity implements PostStateCommunicator {

    Toolbar toolbar;
    TextView toolbarPost;
    String blogName;
    TumblrInterface api;
    EditText postTitle;
    EditText postBody;
    EditText postTags;
    boolean isEdit = false;
    String postId;
    ImageView ivPostState;
    String postState = "published";
    TextView toolbarTitle;
    CoordinatorLayout newPostLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        initViews();
        bindViews();
        api = TumblrService.getClient(NewPostActivity.this).create(TumblrInterface.class);
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_new_post);
        toolbarPost = (TextView) toolbar.findViewById(R.id.tv_make_post);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.new_post_toolbar_title);
        blogName = getIntent().getStringExtra("blog");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create New Post");
        postTitle = (EditText) findViewById(R.id.et_post_title);
        postBody = (EditText) findViewById(R.id.et_post_body);
        postTags = (EditText) findViewById(R.id.et_post_tags);
        ivPostState = (ImageView) findViewById(R.id.iv_post_state);
        newPostLayout = (CoordinatorLayout) findViewById(R.id.new_post_coordinator);

        isEdit = getIntent().getBooleanExtra("edit",false);
        toolbarTitle.setText(" Create New Post");
        if(isEdit){
            postBody.setText(getIntent().getStringExtra("body"));
            postTitle.setText(getIntent().getStringExtra("title"));
            String tags = getIntent().getStringExtra("tags");
            postId = getIntent().getStringExtra("postid");
            int size = tags.length();
            postTags.setText(tags.substring(1,size-1));
            toolbarPost.setText("Update");
            toolbarTitle.setText(" Edit Post");
            ivPostState.setVisibility(View.GONE);
        }
    }

    private void bindViews() {

        toolbarPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEdit){
                    if(!postTitle.getText().toString().equals("")||!postBody.getText().toString().equals(""))
                        makePost();
                    else{
                        Snackbar snackbar = Snackbar
                                .make(newPostLayout,"Enter either Title or Body to Post!", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }

                }
                else{
                    if(!postTitle.getText().toString().equals("")||!postBody.getText().toString().equals(""))
                        editPost();
                    else{
                        Snackbar snackbar = Snackbar
                                .make(newPostLayout,"Enter either Title or Body to Post!", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }

            }
        });

        ivPostState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PostStateSheetFragment().show(getSupportFragmentManager(),"PostState");
            }
        });
    }

    public void makePost()
    {
        Call<DeleteResponse> call = api.createPost(blogName,"text",postState,postTags.getText().equals("")?null:(postTags.getText().toString()),postTitle.getText().equals("")?null:postTitle.getText().toString(),postBody.getText().equals("")?null:postBody.getText().toString());
        call.enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                if(response.code()==201){
                    if(response.body().getMeta().getStatus()==201){
                        setResult(Activity.RESULT_OK);
                        finish();
                    }
                }
                else {
                    Toast.makeText(NewPostActivity.this, "Error Creating Post", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_CANCELED);
                }

            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Toast.makeText(NewPostActivity.this, "Error Creating Post", Toast.LENGTH_SHORT).show();
                setResult(Activity.RESULT_CANCELED);
            }
        });

    }

    public void editPost()
    {
        Call<DeleteResponse> call = api.editPost(blogName,postId,"text",postTags.getText().equals("")?null:(postTags.getText().toString()),postTitle.getText().equals("")?null:postTitle.getText().toString(),postBody.getText().equals("")?null:postBody.getText().toString());
        call.enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                if(response.code()==200){
                    if(response.body().getMeta().getStatus()==200){
                        setResult(Activity.RESULT_OK);
                        finish();
                    }
                }
                else {
                    Toast.makeText(NewPostActivity.this, "Edit Failed", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_CANCELED);
                }

            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Toast.makeText(NewPostActivity.this, "Edit Failed", Toast.LENGTH_SHORT).show();
                setResult(Activity.RESULT_CANCELED);
            }
        });
    }

    @Override
    public void postState(String state) {
        postState = state;
    }
}
