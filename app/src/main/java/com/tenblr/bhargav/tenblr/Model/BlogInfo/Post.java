package com.tenblr.bhargav.tenblr.Model.BlogInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by bhargav on 16/11/16.
 */

public class Post {

    @SerializedName("blog_name")
    @Expose
    private String blogName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("post_url")
    @Expose
    private String postUrl;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("reblog_key")
    @Expose
    private String reblogKey;
    @SerializedName("tags")
    @Expose
    private ArrayList<String> tags = new ArrayList<String>();
    @SerializedName("short_url")
    @Expose
    private String shortUrl;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("recommended_source")
    @Expose
    private Object recommendedSource;
    @SerializedName("recommended_color")
    @Expose
    private Object recommendedColor;
    @SerializedName("followed")
    @Expose
    private Boolean followed;
    @SerializedName("liked")
    @Expose
    private Boolean liked;
    @SerializedName("note_count")
    @Expose
    private Integer noteCount;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("reblog")
    @Expose
    private Reblog reblog;
    @SerializedName("trail")
    @Expose
    private ArrayList<Trail> trail = new ArrayList<Trail>();
    @SerializedName("can_like")
    @Expose
    private Boolean canLike;
    @SerializedName("can_reblog")
    @Expose
    private Boolean canReblog;
    @SerializedName("can_send_in_message")
    @Expose
    private Boolean canSendInMessage;
    @SerializedName("can_reply")
    @Expose
    private Boolean canReply;
    @SerializedName("display_avatar")
    @Expose
    private Boolean displayAvatar;

    public String getBlogName() {
        return blogName;
    }

    public String getId() {
        return id;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public String getSlug() {
        return slug;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public String getState() {
        return state;
    }

    public String getFormat() {
        return format;
    }

    public String getReblogKey() {
        return reblogKey;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getSummary() {
        return summary;
    }

    public Object getRecommendedSource() {
        return recommendedSource;
    }

    public Object getRecommendedColor() {
        return recommendedColor;
    }

    public Boolean getFollowed() {
        return followed;
    }

    public Boolean getLiked() {
        return liked;
    }

    public Integer getNoteCount() {
        return noteCount;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Reblog getReblog() {
        return reblog;
    }

    public ArrayList<Trail> getTrail() {
        return trail;
    }

    public Boolean getCanLike() {
        return canLike;
    }

    public Boolean getCanReblog() {
        return canReblog;
    }

    public Boolean getCanSendInMessage() {
        return canSendInMessage;
    }

    public Boolean getCanReply() {
        return canReply;
    }

    public Boolean getDisplayAvatar() {
        return displayAvatar;
    }
}
