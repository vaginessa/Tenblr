package com.tenblr.bhargav.tenblr.API;

import com.tenblr.bhargav.tenblr.Models.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bhargav on 15/11/16.
 */

public interface TumblrInterface {

    @GET("user/info")
    Call<UserInfoResponse> getUserInfo();

    @GET("blog/{blogname}/avatar")
    Call<Void> getBlogAvatar(@Path("blogname") String blogname);
}
