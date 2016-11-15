package com.tenblr.bhargav.tenblr.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bhargav on 15/11/16.
 */

public interface TumblrInterface {

    @GET("blog/{blog}/posts/submission")
    Call<SampleModel> getTopRatedMovies(@Path("blog") String blogName);
}
