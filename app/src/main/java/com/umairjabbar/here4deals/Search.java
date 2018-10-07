package com.umairjabbar.here4deals;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.umairjabbar.here4deals.DrawerMenuClasses.Buy;
import com.umairjabbar.here4deals.DrawerMenuClasses.Cart;
import com.umairjabbar.here4deals.UtillityClasses.NetworkUtils;

public class Search extends AppCompatActivity {

    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private String postUrl = "https://www.here4deals.com/search";
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
        mNavigationView = findViewById(R.id.navigation);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToolbar = findViewById(R.id.app_bar);

        mToggle = new ActionBarDrawerToggle(Search.this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        mToolbar.setNavigationIcon(R.drawable.ic_dehaze_black_24dp);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);

        App home = new App(Search.this, mNavigationView);
        home.setNavigationItemSelectedListener();

        if(!NetworkUtils.isConnected(Search.this)){
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            webView.setVisibility(View.GONE);
        } else {
            webView.setVisibility(View.VISIBLE);

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
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.view_as_list_or_block:
                Intent intent1 = new Intent(Search.this, GalleryActivity.class);
                startActivity(intent1);
                break;
            case R.id.cart:
                Intent intent = new Intent(Search.this, Cart.class);
                startActivity(intent);
                break;
        }
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
    }
}
