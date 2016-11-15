package com.tenblr.bhargav.tenblr.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tenblr.bhargav.tenblr.Models.Blog;
import com.tenblr.bhargav.tenblr.R;

import java.util.ArrayList;

/**
 * Created by bhargav on 15/11/16.
 */

public class BlogListAdapter extends RecyclerView.Adapter<BlogListAdapter.BlogListViewHolder> {

    ArrayList<Blog> blogs = new ArrayList<>();


    public BlogListAdapter(ArrayList<Blog> data) {
        blogs = data;
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
        holder.postCount.setText("Number of Posts: "+blogs.get(position).getTotalPosts());
        holder.blogUrl.setText(blogs.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return blogs.size();
    }

    public class BlogListViewHolder extends RecyclerView.ViewHolder {

        TextView blogName;
        TextView postCount;
        TextView blogUrl;

        public BlogListViewHolder(View itemView) {
            super(itemView);

            blogName = (TextView) itemView.findViewById(R.id.tv_blog_name);
            postCount = (TextView) itemView.findViewById(R.id.tv_post_count);
            blogUrl = (TextView) itemView.findViewById(R.id.tv_blog_url);
        }
    }

    public void updateData(ArrayList<Blog> data)
    {
        blogs = data;
        notifyDataSetChanged();
    }
}
