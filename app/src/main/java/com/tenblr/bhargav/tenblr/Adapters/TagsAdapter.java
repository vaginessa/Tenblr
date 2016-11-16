package com.tenblr.bhargav.tenblr.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tenblr.bhargav.tenblr.R;

import java.util.ArrayList;

/**
 * Created by bhargav on 16/11/16.
 */

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagViewHolder> {

    ArrayList<String> tags = new ArrayList<>();


    public TagsAdapter(ArrayList<String> data) {
        tags = data;
    }

    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tags,parent,false);
        TagViewHolder tvh = new TagViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {
        holder.tagName.setText("#"+tags.get(position));
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public class TagViewHolder extends RecyclerView.ViewHolder {

        TextView tagName;
        public TagViewHolder(View itemView) {
            super(itemView);
            tagName = (TextView) itemView.findViewById(R.id.tv_tag);
        }
    }
}
