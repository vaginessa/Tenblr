package com.tenblr.bhargav.tenblr.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bhargav on 15/11/16.
 */

public class UserResponse {


    @SerializedName("user")
    @Expose
    private User user;

    /**
     *
     * @return
     * The user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
