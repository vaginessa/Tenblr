package com.tenblr.bhargav.tenblr.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tenblr.bhargav.tenblr.Model.UserInfo.Blog;
import com.tenblr.bhargav.tenblr.R;
import com.tenblr.bhargav.tenblr.UI.Activities.UserDashActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by bhargav on 15/11/16.
 */

public class BlogListAdapter extends RecyclerView.Adapter<BlogListAdapter.BlogListViewHolder> {

    ArrayList<Blog> blogs = new ArrayList<>();

    UserDashActivity act;

    public BlogListAdapter(ArrayList<Blog> data,Activity activity) {
        blogs = data;
        act = (UserDashActivity) activity;
    }

    @Override
    public BlogListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog_list,parent,false);
        BlogListViewHolder bvh = new BlogListViewHolder(v);
        return bvh;
    }

    @Override
    public void onBindViewHolder(BlogListViewHolder holder, int position) {
        holder.blogName.setText(blogs.get(position).getTitle());
        holder.blogUrl.setText(blogs.get(position).getUrl());

        int[] androidColors = act.getResources().getIntArray(R.array.androidcolors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        holder.itemView.setBackgroundColor(randomAndroidColor);
    }

    @Override
    public int getItemCount() {
        return blogs.size();
    }

    public class BlogListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView blogName;
        TextView blogUrl;

        public BlogListViewHolder(View itemView) {
            super(itemView);

            blogName = (TextView) itemView.findViewById(R.id.tv_blog_name);
            blogUrl = (TextView) itemView.findViewById(R.id.tv_blog_url);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            act.loadPostList(blogs.get(getAdapterPosition()).getName());
        }
    }

    public void updateData(ArrayList<Blog> data)
    {
        blogs = data;
        notifyDataSetChanged();
    }


}
