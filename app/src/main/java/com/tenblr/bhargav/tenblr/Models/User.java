package com.tenblr.bhargav.tenblr.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by bhargav on 15/11/16.
 */

public class User {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("following")
    @Expose
    private Integer following;
    @SerializedName("default_post_format")
    @Expose
    private String defaultPostFormat;
    @SerializedName("blogs")
    @Expose
    private ArrayList<Blog> blogs = new ArrayList<Blog>();

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The likes
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     *
     * @param likes
     * The likes
     */
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    /**
     *
     * @return
     * The following
     */
    public Integer getFollowing() {
        return following;
    }

    /**
     *
     * @param following
     * The following
     */
    public void setFollowing(Integer following) {
        this.following = following;
    }

    /**
     *
     * @return
     * The defaultPostFormat
     */
    public String getDefaultPostFormat() {
        return defaultPostFormat;
    }

    /**
     *
     * @param defaultPostFormat
     * The default_post_format
     */
    public void setDefaultPostFormat(String defaultPostFormat) {
        this.defaultPostFormat = defaultPostFormat;
    }

    /**
     *
     * @return
     * The blogs
     */
    public ArrayList<Blog> getBlogs() {
        return blogs;
    }

    /**
     *
     * @param blogs
     * The blogs
     */
    public void setBlogs(ArrayList<Blog> blogs) {
        this.blogs = blogs;
    }
}
