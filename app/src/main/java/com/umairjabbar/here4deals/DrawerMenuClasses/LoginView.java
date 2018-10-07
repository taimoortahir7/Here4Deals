package com.umairjabbar.here4deals.DrawerMenuClasses;

import android.graphics.Bitmap;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.umairjabbar.here4deals.App;
import com.umairjabbar.here4deals.R;
import com.umairjabbar.here4deals.UtillityClasses.NetworkUtils;

public class LoginView extends AppCompatActivity {

    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private String postUrl = "https://www.here4deals.com/login";
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
        mNavigationView = findViewById(R.id.navigation);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToolbar = findViewById(R.id.app_bar);

        mToggle = new ActionBarDrawerToggle(LoginView.this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        mToolbar.setNavigationIcon(R.drawable.ic_dehaze_black_24dp);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);

        App home = new App(LoginView.this, mNavigationView);
        home.setNavigationItemSelectedListener();

        if(!NetworkUtils.isConnected(LoginView.this)){
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        } else {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setHorizontalScrollBarEnabled(false);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    view.loadUrl("javascript:(function() { " +
                            "var head = document.getElementsByClassName('branding-header')[0].style.display='none'; " +
                            "var head = document.getElementsByClassName('search-bar')[0].style.display='none'; " +
                            "var head = document.getElementsByClassName('container-footer')[0].style.display='none'; " +
                            "var head = document.getElementsByClassName('credits')[0].style.display='none'; " +
                            "var head = document.getElementById('user-feedback').style.display='none'; " +
                            "})()");
                    progressBar.setVisibility(View.GONE);
                }
            });
            webView.loadUrl(postUrl);
            webView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
    }
}
