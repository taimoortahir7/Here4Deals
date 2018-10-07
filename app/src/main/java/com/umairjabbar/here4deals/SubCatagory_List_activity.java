package com.umairjabbar.here4deals;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.umairjabbar.here4deals.DrawerMenuClasses.Cart;

import java.util.ArrayList;
import java.util.List;

public class SubCatagory_List_activity extends AppCompatActivity {


    private Toolbar mToolbar;
    private EditText mSearch;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;


    private List<GalleryActivity.Categories> categories = new ArrayList<>();
    private GalleryActivity.GridViewAdapter adapter;
    ListView listView;
    ArrayList prgmName;

    public static int[] prgmImages = {R.drawable.book, R.drawable.car, R.drawable.fashion, R.drawable.laptop, R.drawable.job};
    public static String[] prgmNameList = {"Books", "Cars", "Fashion", "Laptops", "Jobs"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_catagory__list_activity);

        // initControls();

        listView = (ListView) findViewById(R.id.home_list);
        listView.setAdapter(new AdapterForSubCatagory(this, prgmNameList, prgmImages));


    }

    private void initControls() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);



        String[] images = new String[2];


        mToggle = new ActionBarDrawerToggle(SubCatagory_List_activity.this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        mToolbar.setNavigationIcon(R.drawable.ic_dehaze_black_24dp);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);

        App home = new App(SubCatagory_List_activity.this, mNavigationView);
        home.setNavigationItemSelectedListener();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_right_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.search_item:
                Intent intent1 = new Intent(SubCatagory_List_activity.this, Search.class);
                startActivity(intent1);
                break;
            case R.id.view_as_list_or_block:
                Intent intent = new Intent(SubCatagory_List_activity.this, GalleryActivity.class);
                startActivity(intent);
                break;
            case R.id.cart:
                Intent intent2 = new Intent(SubCatagory_List_activity.this, Cart.class);
                startActivity(intent2);
                break;
        }


        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
    }
}
