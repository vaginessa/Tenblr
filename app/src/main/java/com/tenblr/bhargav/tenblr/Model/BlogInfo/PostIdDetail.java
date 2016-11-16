package com.tenblr.bhargav.tenblr.Model.BlogInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bhargav on 16/11/16.
 */

public class PostIdDetail {

    @SerializedName("id")
    @Expose
    private String id;


    public String getId() {
        return id;
    }
}
