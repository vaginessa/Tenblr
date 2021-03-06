package com.tenblr.bhargav.tenblr.UI.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.scribejava.apis.TumblrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.tenblr.bhargav.tenblr.BuildConfig;
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
    boolean isLogout = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth_login);
        pref = new PrefUtil(this);
        webView = (WebView) findViewById(R.id.login_webview);
        if(isNetworkStatusAvialable(this))
            new TumblrOAuth().execute();
        else{
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(OAuthLoginActivity.this,UserDashActivity.class);
            in.putExtra("login",false);
            startActivity(in);
            finish();
        }

        if(getIntent()!=null)
            isLogout = getIntent().getBooleanExtra("logout",false);
    }

    public void loadAuthWebView(String authUrl)
    {
        if(isLogout){
            webView.clearCache(true);
            webView.clearHistory();
            webView.clearFormData();
            clearCookies(this);
        }

        if(oAuthVerifier ==null && oAuthToken ==null) {
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
                        finish();
                        return true;
                    }
                    return super.shouldOverrideUrlLoading(view, url);
                }
            });
            webView.loadUrl(authUrl);
        }
        else {
            Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
        }
    }

    public void storeCredentials(String accessToken,String accessSecret) {
        pref.setStringPref(Constants.OAUTH_TOKEN, accessToken);
        pref.setStringPref(Constants.OAUTH_SECRET, accessSecret);
        pref.setBooleanPref(Constants.LOGGED_IN,true);
        pref.commit();
        pref.setBooleanPref(Constants.LOGGED_IN,true);
    }


    public static void clearCookies(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        }
        else {
            CookieSyncManager cookieSyncMngr=CookieSyncManager.createInstance(context);
            cookieSyncMngr.startSync();
            CookieManager cookieManager=CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }

    class getAccessToken extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Token accesstoken = service.getAccessToken(reqToken,oAuthVerifier);
                storeCredentials(accesstoken.getParameter(Constants.OAUTH_TOKEN),accesstoken.getParameter(Constants.OAUTH_SECRET));
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
                service = new ServiceBuilder().apiKey(BuildConfig.CONSUMER_KEY)
                        .apiSecret(BuildConfig.CONSUMER_SECRET).callback(callbackUrl)
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

    public static boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return true;
        }
        return false;
    }
}
