package com.umairjabbar.here4deals;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.umairjabbar.here4deals.DrawerMenuClasses.Cart;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    class Categories {
        private String id;
        private String image;
        private String title;

        Categories(String id, String image, String title){
            this.id = id;
            this.image = image;
            this.title = title;
        }

        public String getId(){
            return id;
        }
        public String getImage(){
            return image;
        }
        public String getTitle(){
            return title;
        }
    }


    private Toolbar mToolbar;
    private EditText mSearch;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private GridView mGridView;

    private List<Categories> categories = new ArrayList<>();
    private GridViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        initControls();

    }

    private void initControls(){
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        mSearch = (EditText) findViewById(R.id.search_edit_text);
        mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mGridView = (GridView) findViewById(R.id.grid_view);

        mToggle = new ActionBarDrawerToggle(GalleryActivity.this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        mToolbar.findViewById(R.id.logo).setVisibility(View.GONE);
        mToolbar.setNavigationIcon(R.drawable.ic_dehaze_black_24dp);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Categories</font>"));


        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        App home = new App(GalleryActivity.this,mNavigationView);
        home.setNavigationItemSelectedListener();


        categories.add(new Categories("1","http://www.here4deals.com/uploads/cache/s-l1600-samsung101bluecase-500x500.jpg","Category 1"));
        categories.add(new Categories("2","http://www.here4deals.com/uploads/cache/s-l1600-samsung101bluecase-500x500.jpg","Category 2"));

        adapter = new GridViewAdapter(GalleryActivity.this,R.layout.gallery_grid_item,categories);

        mGridView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_right_menu,menu);
        menu.removeItem(R.id.search_item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.search_item:
                Intent intent = new Intent(GalleryActivity.this, Search.class);
                startActivity(intent);
                break;
            case R.id.view_as_list_or_block:
                break;
            case R.id.cart:
                Intent intent2 = new Intent(GalleryActivity.this, Cart.class);
                startActivity(intent2);
                break;
        }


        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    class GridViewAdapter extends ArrayAdapter<Categories>{
        private Context ctx;
        private List<Categories> mOriginalCategories;
        private List<Categories> mDisplayedCategories;
        public GridViewAdapter(@NonNull Context context, @LayoutRes int resource, List<Categories> list) {
            super(context, resource);
            this.ctx = context;
            mOriginalCategories = list;
            mDisplayedCategories = list;
        }

        @Nullable
        @Override
        public Categories getItem(int position) {
            return mDisplayedCategories.get(position);
        }


        @Override
        public int getCount() {
            return mDisplayedCategories.size();
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View item = convertView;

            if (item == null)
                item = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_grid_item, parent, false);

            ((TextView) item.findViewById(R.id.gallery_title)).setText(getItem(position).getTitle());
            ImageView imageView = (ImageView) item.findViewById(R.id.gallery_image);
            new GetImageWithUrl(imageView).execute(getItem(position).getImage());
            imageView.getLayoutParams().height = (int)(getWindowManager().getDefaultDisplay().getWidth()/2)-40;

            item.setTag(getItem(position).getId());

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    App.showToast(ctx,v.getTag().toString());

                    Intent intent = new Intent(GalleryActivity.this, SubCatagory_List_activity.class);

                    startActivity(intent);

                }
            });

            return item;
        }

        @NonNull
        @Override
        public Filter getFilter() {
            return new Filter() {
                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    mDisplayedCategories = (ArrayList<Categories>) results.values; // has the filtered values
                    notifyDataSetChanged();  // notifies the data with new filtered values
                }

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                    ArrayList<Categories> FilteredArrList = new ArrayList<Categories>();

                    if (mOriginalCategories == null) {
                        mOriginalCategories = new ArrayList<Categories>(mDisplayedCategories); // saves the original data in mOriginalValues
                    }

                    /* *******
                     *
                     *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                     *  else does the Filtering and returns FilteredArrList(Filtered)
                     *
                     ********/
                    if (constraint == null || constraint.length() == 0) {

                        // set the Original result to return
                        results.count = mOriginalCategories.size();
                        results.values = mOriginalCategories;

                    } else {
                        constraint = constraint.toString().toLowerCase();
                        for (int i = 0; i < mOriginalCategories.size(); i++) {
                            String data = mOriginalCategories.get(i).getTitle();
                            if (data.toLowerCase().startsWith(constraint.toString())) {
                                FilteredArrList.add(new Categories(mOriginalCategories.get(i).getId(),mOriginalCategories.get(i).getImage(),mOriginalCategories.get(i).getTitle()));
                            }
                        }

                        // set the Filtered result to return

                        results.count = FilteredArrList.size();
                        results.values = FilteredArrList;
                    }
                    return results;
                }

            };
        }

    }

    @Override
    public void onBackPressed() {
    }
}
