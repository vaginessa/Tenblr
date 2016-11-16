package com.tenblr.bhargav.tenblr.Model.UserInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bhargav on 15/11/16.
 */

public class Blog {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("total_posts")
    @Expose
    private Integer totalPosts;
    @SerializedName("posts")
    @Expose
    private Integer posts;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("updated")
    @Expose
    private Integer updated;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("is_nsfw")
    @Expose
    private Boolean isNsfw;
    @SerializedName("ask")
    @Expose
    private Boolean ask;
    @SerializedName("ask_page_title")
    @Expose
    private String askPageTitle;
    @SerializedName("ask_anon")
    @Expose
    private Boolean askAnon;
    @SerializedName("followed")
    @Expose
    private Boolean followed;
    @SerializedName("can_send_fan_mail")
    @Expose
    private Boolean canSendFanMail;
    @SerializedName("is_blocked_from_primary")
    @Expose
    private Boolean isBlockedFromPrimary;
    @SerializedName("share_likes")
    @Expose
    private Boolean shareLikes;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("twitter_enabled")
    @Expose
    private Boolean twitterEnabled;
    @SerializedName("twitter_send")
    @Expose
    private Boolean twitterSend;
    @SerializedName("facebook_opengraph_enabled")
    @Expose
    private String facebookOpengraphEnabled;
    @SerializedName("tweet")
    @Expose
    private String tweet;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("followers")
    @Expose
    private Integer followers;
    @SerializedName("primary")
    @Expose
    private Boolean primary;
    @SerializedName("admin")
    @Expose
    private Boolean admin;
    @SerializedName("messages")
    @Expose
    private Integer messages;
    @SerializedName("queue")
    @Expose
    private Integer queue;
    @SerializedName("drafts")
    @Expose
    private Integer drafts;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("reply_conditions")
    @Expose
    private Integer replyConditions;
    @SerializedName("subscribed")
    @Expose
    private Boolean subscribed;
    @SerializedName("can_subscribe")
    @Expose
    private Boolean canSubscribe;

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

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

    /**
     *
     * @return
     * The posts
     */
    public Integer getPosts() {
        return posts;
    }

    /**
     *
     * @param posts
     * The posts
     */
    public void setPosts(Integer posts) {
        this.posts = posts;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The updated
     */
    public Integer getUpdated() {
        return updated;
    }

    /**
     *
     * @param updated
     * The updated
     */
    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The isNsfw
     */
    public Boolean getIsNsfw() {
        return isNsfw;
    }

    /**
     *
     * @param isNsfw
     * The is_nsfw
     */
    public void setIsNsfw(Boolean isNsfw) {
        this.isNsfw = isNsfw;
    }

    /**
     *
     * @return
     * The ask
     */
    public Boolean getAsk() {
        return ask;
    }

    /**
     *
     * @param ask
     * The ask
     */
    public void setAsk(Boolean ask) {
        this.ask = ask;
    }

    /**
     *
     * @return
     * The askPageTitle
     */
    public String getAskPageTitle() {
        return askPageTitle;
    }

    /**
     *
     * @param askPageTitle
     * The ask_page_title
     */
    public void setAskPageTitle(String askPageTitle) {
        this.askPageTitle = askPageTitle;
    }

    /**
     *
     * @return
     * The askAnon
     */
    public Boolean getAskAnon() {
        return askAnon;
    }

    /**
     *
     * @param askAnon
     * The ask_anon
     */
    public void setAskAnon(Boolean askAnon) {
        this.askAnon = askAnon;
    }

    /**
     *
     * @return
     * The followed
     */
    public Boolean getFollowed() {
        return followed;
    }

    /**
     *
     * @param followed
     * The followed
     */
    public void setFollowed(Boolean followed) {
        this.followed = followed;
    }

    /**
     *
     * @return
     * The canSendFanMail
     */
    public Boolean getCanSendFanMail() {
        return canSendFanMail;
    }

    /**
     *
     * @param canSendFanMail
     * The can_send_fan_mail
     */
    public void setCanSendFanMail(Boolean canSendFanMail) {
        this.canSendFanMail = canSendFanMail;
    }

    /**
     *
     * @return
     * The isBlockedFromPrimary
     */
    public Boolean getIsBlockedFromPrimary() {
        return isBlockedFromPrimary;
    }

    /**
     *
     * @param isBlockedFromPrimary
     * The is_blocked_from_primary
     */
    public void setIsBlockedFromPrimary(Boolean isBlockedFromPrimary) {
        this.isBlockedFromPrimary = isBlockedFromPrimary;
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
     * The twitterEnabled
     */
    public Boolean getTwitterEnabled() {
        return twitterEnabled;
    }

    /**
     *
     * @param twitterEnabled
     * The twitter_enabled
     */
    public void setTwitterEnabled(Boolean twitterEnabled) {
        this.twitterEnabled = twitterEnabled;
    }

    /**
     *
     * @return
     * The twitterSend
     */
    public Boolean getTwitterSend() {
        return twitterSend;
    }

    /**
     *
     * @param twitterSend
     * The twitter_send
     */
    public void setTwitterSend(Boolean twitterSend) {
        this.twitterSend = twitterSend;
    }

    /**
     *
     * @return
     * The facebookOpengraphEnabled
     */
    public String getFacebookOpengraphEnabled() {
        return facebookOpengraphEnabled;
    }

    /**
     *
     * @param facebookOpengraphEnabled
     * The facebook_opengraph_enabled
     */
    public void setFacebookOpengraphEnabled(String facebookOpengraphEnabled) {
        this.facebookOpengraphEnabled = facebookOpengraphEnabled;
    }

    /**
     *
     * @return
     * The tweet
     */
    public String getTweet() {
        return tweet;
    }

    /**
     *
     * @param tweet
     * The tweet
     */
    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    /**
     *
     * @return
     * The facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     *
     * @param facebook
     * The facebook
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     *
     * @return
     * The followers
     */
    public Integer getFollowers() {
        return followers;
    }

    /**
     *
     * @param followers
     * The followers
     */
    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    /**
     *
     * @return
     * The primary
     */
    public Boolean getPrimary() {
        return primary;
    }

    /**
     *
     * @param primary
     * The primary
     */
    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    /**
     *
     * @return
     * The admin
     */
    public Boolean getAdmin() {
        return admin;
    }

    /**
     *
     * @param admin
     * The admin
     */
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    /**
     *
     * @return
     * The messages
     */
    public Integer getMessages() {
        return messages;
    }

    /**
     *
     * @param messages
     * The messages
     */
    public void setMessages(Integer messages) {
        this.messages = messages;
    }

    /**
     *
     * @return
     * The queue
     */
    public Integer getQueue() {
        return queue;
    }

    /**
     *
     * @param queue
     * The queue
     */
    public void setQueue(Integer queue) {
        this.queue = queue;
    }

    /**
     *
     * @return
     * The drafts
     */
    public Integer getDrafts() {
        return drafts;
    }

    /**
     *
     * @param drafts
     * The drafts
     */
    public void setDrafts(Integer drafts) {
        this.drafts = drafts;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The replyConditions
     */
    public Integer getReplyConditions() {
        return replyConditions;
    }

    /**
     *
     * @param replyConditions
     * The reply_conditions
     */
    public void setReplyConditions(Integer replyConditions) {
        this.replyConditions = replyConditions;
    }

    /**
     *
     * @return
     * The subscribed
     */
    public Boolean getSubscribed() {
        return subscribed;
    }

    /**
     *
     * @param subscribed
     * The subscribed
     */
    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    /**
     *
     * @return
     * The canSubscribe
     */
    public Boolean getCanSubscribe() {
        return canSubscribe;
    }

    /**
     *
     * @param canSubscribe
     * The can_subscribe
     */
    public void setCanSubscribe(Boolean canSubscribe) {
        this.canSubscribe = canSubscribe;
    }
}
