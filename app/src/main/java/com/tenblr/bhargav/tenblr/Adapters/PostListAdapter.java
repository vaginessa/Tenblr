package com.tenblr.bhargav.tenblr.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tenblr.bhargav.tenblr.Model.BlogInfo.Post;
import com.tenblr.bhargav.tenblr.R;
import com.tenblr.bhargav.tenblr.UI.Activities.NewPostActivity;
import com.tenblr.bhargav.tenblr.UI.Activities.UserDashActivity;

import java.util.ArrayList;

import static com.tenblr.bhargav.tenblr.R.id.iv_edit;

/**
 * Created by bhargav on 16/11/16.
 */

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder> {

    ArrayList<Post> posts = new ArrayList<>();
    UserDashActivity act;
    TagsAdapter adapter;

    int deletePos = -1;
    public PostListAdapter(ArrayList<Post> data, Activity activity) {

        posts = data;
        act = (UserDashActivity) activity;
    }

    @Override
    public PostListAdapter.PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,parent,false);
        PostViewHolder pvh = new PostViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PostListAdapter.PostViewHolder holder, int position) {
        Post post = posts.get(position);

        if(post.getTitle()!=null){
            holder.postTitle.setVisibility(View.VISIBLE);
            holder.postTitle.setText(post.getTitle());
        }
        else{
            holder.postTitle.setVisibility(View.GONE);
        }


        if(post.getBody()!=null){
            holder.postBody.setText(post.getBody());
            holder.postBody.setVisibility(View.VISIBLE);
        }
        else{
            holder.postBody.setVisibility(View.GONE);
        }



        if(post.getTags().size()>0){
            holder.postTags.setVisibility(View.VISIBLE);
            TagsAdapter adapter = new TagsAdapter(post.getTags());
            LinearLayoutManager layoutManager = new LinearLayoutManager(act,LinearLayoutManager.HORIZONTAL,false);
            holder.postTags.setLayoutManager(layoutManager);
            holder.postTags.setAdapter(adapter);
        }
        else
        {
            holder.postTags.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }




    public void deletePost(boolean deleted)
    {
        if(deleted&&deletePos!=-1){
            posts.remove((deletePos));
            notifyItemRemoved(deletePos);
        }

        else
            Toast.makeText(act, "Error Deleting Post", Toast.LENGTH_SHORT).show();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        TextView postTitle;
        TextView postBody;
        RecyclerView postTags;
        ImageView postDelete;
        ImageView postShare;
        ImageView postEdit;



        public PostViewHolder(View v) {
            super(v);

            postTitle = (TextView) v.findViewById(R.id.tv_post_title);
            postBody = (TextView) v.findViewById(R.id.tv_post_body);
            postTags = (RecyclerView) v.findViewById(R.id.recView_tags);
            postDelete = (ImageView) v.findViewById(R.id.iv_delete);
            postShare = (ImageView) v.findViewById(R.id.iv_share);
            postEdit = (ImageView) v.findViewById(iv_edit);


            postDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    act.deletePost(posts.get(getAdapterPosition()).getBlogName(), posts.get(getAdapterPosition()).getId());
                    deletePos = getAdapterPosition();
                }
            });

            postShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out thius post: "+posts.get(getAdapterPosition()).getPostUrl());
                    sendIntent.setType("text/plain");
                    act.startActivity(sendIntent);
                }
            });

            postEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(act, NewPostActivity.class);
                    in.putExtra("edit",true);
                    in.putExtra("blog",posts.get(getAdapterPosition()).getBlogName().toString());
                    in.putExtra("postid",posts.get(getAdapterPosition()).getId());
                    in.putExtra("title",posts.get(getAdapterPosition()).getTitle().toString());
                    in.putExtra("body",posts.get(getAdapterPosition()).getBody().toString());
                    in.putExtra("tags",posts.get(getAdapterPosition()).getTags().toString());
                    act.startActivity(in);
                }
            });
        }
    }

    public void updateData(ArrayList<Post> data){
        posts = data;
        notifyDataSetChanged();
    }
}
