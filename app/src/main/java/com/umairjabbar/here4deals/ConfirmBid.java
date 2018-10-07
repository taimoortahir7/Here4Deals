package com.umairjabbar.here4deals;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umairjabbar.here4deals.DrawerMenuClasses.Cart;
import com.umairjabbar.here4deals.DrawerMenuClasses.Help;

public class ConfirmBid extends AppCompatActivity {

    ImageView closeImageView;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private TextView value_bid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_bid);

        mNavigationView = findViewById(R.id.navigation);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToolbar = findViewById(R.id.app_bar);

        mToggle = new ActionBarDrawerToggle(ConfirmBid.this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        mToolbar.setNavigationIcon(R.drawable.ic_dehaze_black_24dp);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);

        App home = new App(ConfirmBid.this, mNavigationView);
        home.setNavigationItemSelectedListener();

        value_bid = findViewById(R.id.bid_value);
        value_bid.setText("$ " + getIntent().getStringExtra("bid_value"));

        closeImageView = findViewById(R.id.image_close);
        closeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                Intent intent = new Intent(ConfirmBid.this, GalleryActivity.class);
                startActivity(intent);
                break;
            case R.id.cart:
                Intent intent1 = new Intent(ConfirmBid.this, Cart.class);
                startActivity(intent1);
                break;
        }
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
    }
}
