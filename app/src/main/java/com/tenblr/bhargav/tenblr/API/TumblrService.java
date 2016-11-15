package com.tenblr.bhargav.tenblr.API;

import android.content.Context;

import com.tenblr.bhargav.tenblr.Utils.Constants;
import com.tenblr.bhargav.tenblr.Utils.PrefUtil;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by bhargav on 15/11/16.
 */

public class TumblrService {

    public static final String BASE_URL = "https://api.tumblr.com/v2/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient(Context context)
    {

        if(retrofit==null)
        {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
            consumer.setTokenWithSecret(new PrefUtil(context).getStringPref(Constants.OAUTH_TOKEN,""),
                    new PrefUtil(context).getStringPref(Constants.OAUTH_SECRET,""));

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new SigningInterceptor(consumer)).addInterceptor(interceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(client)
                    .build();
        }
    return retrofit;
    }





}
