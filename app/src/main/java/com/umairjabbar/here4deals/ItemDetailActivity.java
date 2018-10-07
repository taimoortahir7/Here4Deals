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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.umairjabbar.here4deals.DrawerMenuClasses.Cart;

public class ItemDetailActivity extends AppCompatActivity {

    AdView adView;
    EditText bid_editText;
    Button bid_button;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        mNavigationView = findViewById(R.id.navigation);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToolbar = findViewById(R.id.app_bar);

        mToggle = new ActionBarDrawerToggle(ItemDetailActivity.this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        mToolbar.setNavigationIcon(R.drawable.ic_dehaze_black_24dp);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);

        App home = new App(ItemDetailActivity.this, mNavigationView);
        home.setNavigationItemSelectedListener();


        bid_button = findViewById(R.id.button);
        bid_editText = findViewById(R.id.editText);

        adView = findViewById(R.id.adView);
        adMobTask();

        bid_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemDetailActivity.this, ConfirmBid.class);
                intent.putExtra("bid_value", bid_editText.getText().toString());
                startActivity(intent);
            }
        });
    }



    int imageStatus = 0;
    public void imageChanger(View view)
    {

        ImageView myImage=(ImageView)findViewById(R.id.bigimg);

        if (imageStatus == 0) {
            myImage.setImageResource(R.drawable.fashion);
            imageStatus = 1;
        }
        else if (imageStatus == 1){
            myImage.setImageResource(R.drawable.car);
            imageStatus = 2;
        }
        else if (imageStatus == 2) {
            myImage.setImageResource(R.drawable.book);
            imageStatus = 0;
        }
        else {
            myImage.setImageResource(R.drawable.fashion);
            imageStatus = 0;
        }
    }

    private void adMobTask() {
        AdRequest adRequest = new AdRequest.Builder() .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
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
                Intent intent = new Intent(ItemDetailActivity.this, GalleryActivity.class);
                startActivity(intent);
                break;
            case R.id.cart:
                Intent intent1 = new Intent(ItemDetailActivity.this, Cart.class);
                startActivity(intent1);
                break;
        }
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
    }
}

