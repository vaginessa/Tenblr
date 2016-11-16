package com.tenblr.bhargav.tenblr.Model.BlogInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bhargav on 16/11/16.
 */

public class PostBlogDetail {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("theme")
    @Expose
    private Theme theme;
    @SerializedName("share_likes")
    @Expose
    private Boolean shareLikes;
    @SerializedName("share_following")
    @Expose
    private Boolean shareFollowing;
    @SerializedName("can_be_followed")
    @Expose
    private Boolean canBeFollowed;

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
     * The active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     *
     * @param active
     * The active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     *
     * @return
     * The theme
     */
    public Theme getTheme() {
        return theme;
    }

    /**
     *
     * @param theme
     * The theme
     */
    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    /**
     *
     * @return
     * The shareLikes
     */
    public Boolean getShareLikes() {
        return shareLikes;
    }

    /**
     *
     * @param shareLikes
     * The share_likes
     */
    public void setShareLikes(Boolean shareLikes) {
        this.shareLikes = shareLikes;
    }

    /**
     *
     * @return
     * The shareFollowing
     */
    public Boolean getShareFollowing() {
        return shareFollowing;
    }

    /**
     *
     * @param shareFollowing
     * The share_following
     */
    public void setShareFollowing(Boolean shareFollowing) {
        this.shareFollowing = shareFollowing;
    }

    /**
     *
     * @return
     * The canBeFollowed
     */
    public Boolean getCanBeFollowed() {
        return canBeFollowed;
    }

    /**
     *
     * @param canBeFollowed
     * The can_be_followed
     */
    public void setCanBeFollowed(Boolean canBeFollowed) {
        this.canBeFollowed = canBeFollowed;
    }

}
