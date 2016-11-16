package com.tenblr.bhargav.tenblr.Model.BlogInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tenblr.bhargav.tenblr.Model.UserInfo.Blog;

import java.util.ArrayList;

/**
 * Created by bhargav on 16/11/16.
 */

public class BlogResponse {

    @SerializedName("blog")
    @Expose
    private Blog blog;
    @SerializedName("posts")
    @Expose
    private ArrayList<Post> posts = new ArrayList<Post>();
    @SerializedName("total_posts")
    @Expose
    private Integer totalPosts;

    /**
     *
     * @return
     * The blog
     */
    public Blog getBlog() {
        return blog;
    }

    /**
     *
     * @param blog
     * The blog
     */
    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    /**
     *
     * @return
     * The posts
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     *
     * @param posts
     * The posts
     */
    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    /**
     *
     * @return
     * The totalPosts
     */
    public Integer getTotalPosts() {
        return totalPosts;
    }

    /**
     *
     * @param totalPosts
     * The total_posts
     */
    public void setTotalPosts(Integer totalPosts) {
        this.totalPosts = totalPosts;
    }
}
