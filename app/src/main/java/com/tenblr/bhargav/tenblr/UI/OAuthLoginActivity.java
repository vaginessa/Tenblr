package com.tenblr.bhargav.tenblr.UI;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.scribejava.apis.TumblrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.tenblr.bhargav.tenblr.R;
import com.tenblr.bhargav.tenblr.Utils.Constants;
import com.tenblr.bhargav.tenblr.Utils.PrefUtil;

public class OAuthLoginActivity extends AppCompatActivity {


    WebView webView;
    private String oAuthUrl;

    private String accessToken = null;
    private String accessSecret = null;

    private String callbackUrl = "https://www.tumblr.com/dashboard";

    PrefUtil pref;

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

        if(accessSecret==null && accessToken ==null)
        {
            webView.getSettings().setJavaScriptEnabled(true);

            webView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                    if(url.startsWith(callbackUrl)&& url.contains("oauth_token"))
                    {
                            Uri uri = Uri.parse(url);
                            accessToken = uri.getQueryParameter("oauth_token");
                            accessSecret = uri.getQueryParameter("oauth_verifier");
                            pref.setStringPref(Constants.OAUTH_TOKEN,accessToken);
                            pref.setStringPref(Constants.OAUTH_SECRET,accessSecret);
                            pref.setBooleanPref(Constants.LOGGED_IN,true);
                            return true;
                    }
                    else if(url.startsWith(callbackUrl))
                    {
                        Toast.makeText(OAuthLoginActivity.this, "Sign In To Continue", Toast.LENGTH_SHORT).show();
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

    class TumblrOAuth extends AsyncTask<Void, Void, Void> {

        private Exception exception;

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                OAuth10aService service = new ServiceBuilder().apiKey("8RoctfJ6jh9h2bzrwpuHmvZUeYEItXMdikPmVHSI6OUtiLDo2E")
                        .apiSecret("HhoCC9FHl9kt2wDVKc2LfJA73IBkVr0iDH5JRFG4goAcl2zH7X").callback(callbackUrl)
                        .build(TumblrApi.instance());

                final OAuth1RequestToken reqToken = service.getRequestToken();

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
