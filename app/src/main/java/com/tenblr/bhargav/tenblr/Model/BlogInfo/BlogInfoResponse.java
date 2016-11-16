package com.tenblr.bhargav.tenblr.Model.BlogInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tenblr.bhargav.tenblr.Model.UserInfo.Meta;

/**
 * Created by bhargav on 16/11/16.
 */

public class BlogInfoResponse {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("response")
    @Expose
    private BlogResponse response;

    public Meta getMeta() {
        return meta;
    }

    public BlogResponse getResponse() {
        return response;
    }
}
