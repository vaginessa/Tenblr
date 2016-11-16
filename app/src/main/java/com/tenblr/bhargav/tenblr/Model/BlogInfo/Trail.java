package com.tenblr.bhargav.tenblr.Model.BlogInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bhargav on 16/11/16.
 */

public class Trail {

    @SerializedName("blog")
    @Expose
    private PostBlogDetail blog;
    @SerializedName("post")
    @Expose
    private PostIdDetail post;
    @SerializedName("content_raw")
    @Expose
    private String contentRaw;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("is_current_item")
    @Expose
    private Boolean isCurrentItem;
    @SerializedName("is_root_item")
    @Expose
    private Boolean isRootItem;

    public PostBlogDetail getBlog() {
        return blog;
    }

    public PostIdDetail getPost() {
        return post;
    }

    public String getContentRaw() {
        return contentRaw;
    }

    public String getContent() {
        return content;
    }

    public Boolean getCurrentItem() {
        return isCurrentItem;
    }

    public Boolean getRootItem() {
        return isRootItem;
    }
}
