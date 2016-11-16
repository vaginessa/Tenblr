package com.tenblr.bhargav.tenblr.UI.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.scribejava.apis.TumblrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.tenblr.bhargav.tenblr.R;
import com.tenblr.bhargav.tenblr.Utils.Constants;
import com.tenblr.bhargav.tenblr.Utils.PrefUtil;

import java.io.IOException;

public class OAuthLoginActivity extends AppCompatActivity {


    WebView webView;
    private String oAuthUrl;

    private String oAuthToken = null;
    private String oAuthVerifier = null;

    private String callbackUrl = "https://www.tumblr.com/dashboard";

    PrefUtil pref;
    private OAuth10aService service;
    private OAuth1RequestToken reqToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth_login);
        pref = new PrefUtil(this);
        webView = (WebView) findViewById(R.id.login_webview);
        new TumblrOAuth().execute();

    }

    public void loadAuthWebView(String authUrl)
    {

        if(oAuthVerifier ==null && oAuthToken ==null)
        {
            webView.getSettings().setJavaScriptEnabled(true);

            webView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                    if(url.startsWith(callbackUrl)&& url.contains("oauth_token"))
                    {
                            Uri uri = Uri.parse(url);
                            oAuthToken = uri.getQueryParameter("oauth_token");
                            oAuthVerifier = uri.getQueryParameter("oauth_verifier");
                            new getAccessToken().execute();
                            return  true;
                    }
                    else if(url.startsWith(callbackUrl))
                    {
                        Intent in = new Intent(OAuthLoginActivity.this,UserDashActivity.class);
                        in.putExtra("login",false);
                        startActivity(in);
                        return true;
                    }
                    return super.shouldOverrideUrlLoading(view, url);
                }
            });

            webView.loadUrl(authUrl);
        }
        else
        {
            Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
        }

    }

    public void storeCredentials(String accessToken,String accessSecret)
    {
        pref.setStringPref(Constants.OAUTH_TOKEN, accessToken);
        pref.setStringPref(Constants.OAUTH_SECRET, accessSecret);
        pref.setBooleanPref(Constants.LOGGED_IN,true);
        pref.commit();
        pref.setBooleanPref(Constants.LOGGED_IN,true);
    }

    class getAccessToken extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Token accesstoken = service.getAccessToken(reqToken,oAuthVerifier);
                Log.v("Access",accesstoken.getRawResponse());
                storeCredentials(accesstoken.getParameter("oauth_token"),accesstoken.getParameter("oauth_token_secret"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent in = new Intent(OAuthLoginActivity.this,UserDashActivity.class);
            in.putExtra("login",true);
            startActivity(in);
            finish();
        }
    }

    class TumblrOAuth extends AsyncTask<Void, Void, Void> {

        private Exception exception;

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                service = new ServiceBuilder().apiKey(Constants.CONSUMER_KEY)
                        .apiSecret(Constants.CONSUMER_SECRET).callback(callbackUrl)
                        .build(TumblrApi.instance());

                reqToken = service.getRequestToken();

                oAuthUrl = service.getAuthorizationUrl(reqToken);

            } catch (Exception e) {
                this.exception = e;

                return null;
            }
            return null;
        }

        protected void onPostExecute(Void d) {
            loadAuthWebView(oAuthUrl);
        }
    }
}
