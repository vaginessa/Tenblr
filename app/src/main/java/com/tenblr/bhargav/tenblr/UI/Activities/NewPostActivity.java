package com.tenblr.bhargav.tenblr.UI.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tenblr.bhargav.tenblr.API.TumblrInterface;
import com.tenblr.bhargav.tenblr.API.TumblrService;
import com.tenblr.bhargav.tenblr.Model.DeleteResponse;
import com.tenblr.bhargav.tenblr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bhargav on 16/11/16.
 */

public class NewPostActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbarPost;
    String blogName;
    TumblrInterface api;

    EditText postTitle;
    EditText postBody;
    EditText postTags;

    boolean isEdit = false;

    String postId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        toolbar = (Toolbar) findViewById(R.id.toolbar_new_post);
        toolbarPost = (TextView) toolbar.findViewById(R.id.tv_make_post);
        blogName = getIntent().getStringExtra("blog");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create New Post");
        api = TumblrService.getClient(NewPostActivity.this).create(TumblrInterface.class);
        initViews();
        bindViews();
        isEdit = getIntent().getBooleanExtra("edit",false);
        if(isEdit){
            postBody.setText(getIntent().getStringExtra("body"));
            postTitle.setText(getIntent().getStringExtra("title"));
            String tags = getIntent().getStringExtra("tags");
            postId = getIntent().getStringExtra("postid");
            int size = tags.length();
            postTags.setText(tags.substring(1,size-1));
            toolbarPost.setText("Update");
        }



    }

    private void initViews() {
        postTitle = (EditText) findViewById(R.id.et_post_title);
        postBody = (EditText) findViewById(R.id.et_post_body);
        postTags = (EditText) findViewById(R.id.et_post_tags);
    }

    private void bindViews() {
        toolbarPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEdit){
                    if(!postTitle.getText().toString().equals("")||!postBody.getText().toString().equals(""))
                        makePost();
                    else
                        Toast.makeText(NewPostActivity.this, "Enter either Title or Body to Post!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!postTitle.getText().toString().equals("")||!postBody.getText().toString().equals(""))
                        editPost();
                    else
                        Toast.makeText(NewPostActivity.this, "Enter either Title or Body to Post!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void makePost()
    {
     Call<DeleteResponse> call = api.createPost(blogName,"text","published",postTags.getText().equals("")?null:(postTags.getText().toString()),postTitle.getText().equals("")?null:postTitle.getText().toString(),postBody.getText().equals("")?null:postBody.getText().toString());
//        Call<DeleteResponse> call = api.createPost(blogName,"text",null,null,"postAttempt","body");

        call.enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                if(response.code()==201){
                    if(response.body().getMeta().getStatus()==201)
                        Toast.makeText(NewPostActivity.this, "Posted Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(NewPostActivity.this, "Error creating Post", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Toast.makeText(NewPostActivity.this, "Error creating Post", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void editPost()
    {
        Call<DeleteResponse> call = api.editPost(blogName,postId,"text","published",postTags.getText().equals("")?null:(postTags.getText().toString()),postTitle.getText().equals("")?null:postTitle.getText().toString(),postBody.getText().equals("")?null:postBody.getText().toString());

        call.enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                if(response.code()==200){
                    if(response.body().getMeta().getStatus()==200)
                        Toast.makeText(NewPostActivity.this, "Post Updated Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(NewPostActivity.this, "Error Updating Post", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Toast.makeText(NewPostActivity.this, "Error Updating Post", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
