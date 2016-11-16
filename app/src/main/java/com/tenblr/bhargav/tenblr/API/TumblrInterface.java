package com.tenblr.bhargav.tenblr.API;

import com.tenblr.bhargav.tenblr.Model.BlogInfo.BlogInfoResponse;
import com.tenblr.bhargav.tenblr.Model.DeleteResponse;
import com.tenblr.bhargav.tenblr.Model.UserInfo.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by bhargav on 15/11/16.
 */

public interface TumblrInterface {

    @GET("user/info")
    Call<UserInfoResponse> getUserInfo();

    @GET("blog/{blogname}/avatar")
    Call<Void> getBlogAvatar(@Path("blogname") String blogName);

    @GET("blog/{blogname}/posts")
    Call<BlogInfoResponse> getPosts(@Path("blogname") String blogName, @Query("type") String type, @Query("filter") String filter);

    @POST("blog/{blogname}/post/delete")
    Call<DeleteResponse> deletePost(@Path("blogname") String blogName,@Query("id") String postId);

    @FormUrlEncoded
    @POST("blog/{blogname}/post")
    Call<DeleteResponse> createPost(@Path("blogname") String blogName, @Field("type") String postType, @Field("state") String state, @Field("tags") String tags, @Field("title") String postTitle, @Field("body") String postBody);

    @FormUrlEncoded
    @POST("blog/{blogname}/post/edit")
    Call<DeleteResponse> editPost(@Path("blogname") String blogName,@Field("id") String postId,@Field("type") String postType, @Field("state") String state, @Field("tags") String tags, @Field("title") String postTitle, @Field("body") String postBody);
}
