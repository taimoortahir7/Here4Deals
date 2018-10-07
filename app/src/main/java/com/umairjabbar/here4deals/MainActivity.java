package com.umairjabbar.here4deals;

/**
 * Created by Umair Jabbar on 02/04/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.umairjabbar.here4deals.DrawerMenuClasses.About;
import com.umairjabbar.here4deals.DrawerMenuClasses.Cart;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    AdView adView;

    boolean doubleBackToExitPressedOnce = false;

    private ViewPager mViewPager;
    private LinearLayout mLayoutDots;
    private RecyclerView mRecyclerView, m1RecyclerView, m2RecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager m1LayoutManager;
    private RecyclerView.LayoutManager m2LayoutManager;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mAdapter1;
    private RecyclerView.Adapter mAdapter2;
    private ArrayList<String> mDataset;
    private RecyclerView mRecyclerView_capsule;
    private RecyclerView.LayoutManager mLayoutManager_capsule;
    private RecyclerView.Adapter mAdapter_capsule;

    public static int[] prgmImages = {R.drawable.book, R.drawable.car, R.drawable.fashion, R.drawable.laptop, R.drawable.job};
    public static String[] prgmNameList = {"Books", "Cars", "Fashion", "Laptops", "Jobs"};
    public static int[] prgmImages1 = {R.drawable.job, R.drawable.laptop, R.drawable.fashion, R.drawable.car, R.drawable.book};
    public static String[] prgmNameList1 = {"Jobs", "Laptops", "Fashion", "Cars", "Books"};
    public static String[] capsuleName = {"SELLING", "BUYING", "CATEGORIES", "FOLLOWING", "WISHLIST"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();
        adMobTask();
       mDataset=new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            mDataset.add("New Title #" +i);

        }
        mRecyclerView =(RecyclerView)findViewById(R.id.recycle_view);
        m1RecyclerView =(RecyclerView)findViewById(R.id.recycle_view1);
        m2RecyclerView = findViewById(R.id.recycle_view2);

        mRecyclerView.setHasFixedSize(true);
        m1RecyclerView.setHasFixedSize(true);
        m2RecyclerView.setHasFixedSize(true);

        mLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        m1LayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        m2LayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        mRecyclerView.setLayoutManager(mLayoutManager);
        m1RecyclerView.setLayoutManager(m1LayoutManager);
        m2RecyclerView.setLayoutManager(m2LayoutManager);

        mAdapter=new MainAdapter(this,mDataset,prgmNameList, prgmImages);
       mAdapter1=new MainAdapter(this,mDataset,prgmNameList1, prgmImages1);
       mAdapter2 = new MainAdapter(this,mDataset,prgmNameList1, prgmImages1);

        mRecyclerView.setAdapter(mAdapter);
        m1RecyclerView.setAdapter(mAdapter1);
        m2RecyclerView.setAdapter(mAdapter2);
        mRecyclerView_capsule =(RecyclerView)findViewById(R.id.recycle_view_capsule);
        mRecyclerView_capsule.setHasFixedSize(true);
        mLayoutManager_capsule=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView_capsule.setLayoutManager(mLayoutManager_capsule);
        mAdapter_capsule=new MainCapsuleAdapter(MainActivity.this,capsuleName);
        mRecyclerView_capsule.setAdapter(mAdapter_capsule);

        /*TabLayout tabLayout = (TabLayout) findViewById(R.id.tb);
        tabLayout.addTab(tabLayout.newTab().setText("SELLING"));
        tabLayout.addTab(tabLayout.newTab().setText("BUYING"));
        tabLayout.addTab(tabLayout.newTab().setText("CATEGORIES"));
        tabLayout.addTab(tabLayout.newTab().setText("FOLLOWING"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pagre);
        final PagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

*/
    }


    private void initControls() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        adView = findViewById(R.id.adView);

        Integer[] images = {R.drawable.banner2, R.drawable.banner6, R.drawable.banner12};

        mViewPager.setAdapter(new MyViewPagerAdapter(MainActivity.this, images));
        mLayoutDots = (LinearLayout) findViewById(R.id.layoutDots);

        mToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        mToolbar.setNavigationIcon(R.drawable.ic_dehaze_black_24dp);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);

        App home = new App(MainActivity.this, mNavigationView);
        home.setNavigationItemSelectedListener();


    }

    private void adMobTask() {
        AdRequest adRequest = new AdRequest.Builder() .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
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
                Intent intent = new Intent(MainActivity.this, Search.class);
                startActivity(intent);
                break;
            case R.id.view_as_list_or_block:
                Intent intent2 = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(intent2);
                break;
            case R.id.cart:
                Intent intent1 = new Intent(MainActivity.this, Cart.class);
                startActivity(intent1);
                break;
        }


        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }


    ViewPager.OnPageChangeListener viewPagerPageChangeListner = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    class MyViewPagerAdapter extends PagerAdapter {
        private Context ctx;
        private Integer[] slideImages;

        public MyViewPagerAdapter(Context ctx, Integer[] slideImages) {
            // super(ctx);
            this.ctx = ctx;
            this.slideImages = slideImages;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.slider_image, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.slide_image);
            imageView.setImageResource(slideImages[position]);
            container.addView(view);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.here4deals.com"));
                    startActivity(intent);
                }
            });
            return view;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        if (doubleBackToExitPressedOnce) {
//            moveTaskToBack(true);
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Press BACK again to EXIT", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce = false;
//            }
//        }, 2000);
    }

    /* public class MyPagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public MyPagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    SellingFragment sellingFragment = new SellingFragment();
                    return sellingFragment;
                case 1:
                    BuyingFragment buyingFragment = new BuyingFragment();
                    return buyingFragment;
                case 2:
                    CategoriesFragment categoriesFragment = new CategoriesFragment();
                    return categoriesFragment;
                case 3:
                    FollowingFragment followingFragment = new FollowingFragment();
                    return followingFragment;


                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }


*/

}
