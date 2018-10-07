package com.umairjabbar.here4deals;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.umairjabbar.here4deals.DrawerMenuClasses.About;
import com.umairjabbar.here4deals.DrawerMenuClasses.Buy;
import com.umairjabbar.here4deals.DrawerMenuClasses.Cart;
import com.umairjabbar.here4deals.DrawerMenuClasses.Following;
import com.umairjabbar.here4deals.DrawerMenuClasses.Help;
import com.umairjabbar.here4deals.DrawerMenuClasses.LoginView;
import com.umairjabbar.here4deals.DrawerMenuClasses.LogoutView;
import com.umairjabbar.here4deals.DrawerMenuClasses.Messages;
import com.umairjabbar.here4deals.DrawerMenuClasses.Privacy;
import com.umairjabbar.here4deals.DrawerMenuClasses.Selling;
import com.umairjabbar.here4deals.DrawerMenuClasses.SignupView;
import com.umairjabbar.here4deals.DrawerMenuClasses.Stores;
import com.umairjabbar.here4deals.DrawerMenuClasses.Terms;
import com.umairjabbar.here4deals.DrawerMenuClasses.Wishlist;

/**
 * Created by Rana Tayyab on 24-Mar-17.
 */

public class App {
    private final Context ctx;
    private final NavigationView mNavigationView;

    public App(Context context, NavigationView navigationView) {
        ctx = context;
        mNavigationView = navigationView;
    }

    public void setNavigationItemSelectedListener(){
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Intent i = null;

                switch (item.getItemId()){
                    case R.id.home:
                        i = (ctx instanceof MainActivity)? null : new Intent(new Intent(ctx, MainActivity.class));
                        break;
                    case R.id.category:
                        i = (ctx instanceof GalleryActivity)? null : new Intent(ctx, GalleryActivity.class);
                        break;
                    case R.id.listing_items:
                        i = (ctx instanceof Listing_Items)? null : new Intent(ctx, Listing_Items.class);
                        break;
                    case R.id.about:
                        i = (ctx instanceof About)? null : new Intent(ctx, About.class);
                        break;
                    case R.id.messages:
                        i = (ctx instanceof Messages)? null : new Intent(ctx, Messages.class);
                        break;
                    case R.id.help:
                        i = (ctx instanceof Help)? null : new Intent(ctx, Help.class);
                        break;
                    case R.id.buy:
                        i = (ctx instanceof Buy)? null : new Intent(ctx, Buy.class);
                        break;
                    case R.id.sell:
                        i = (ctx instanceof Selling)? null : new Intent(ctx, Selling.class);
                        break;
                    case R.id.classifieds:
                        i = (ctx instanceof GalleryActivity)? null : new Intent(ctx, GalleryActivity.class);
                        break;
                    case R.id.cart:
                        i = (ctx instanceof Cart)? null : new Intent(ctx, Cart.class);
                        break;
                    case R.id.conditions:
                        i = (ctx instanceof Terms)? null : new Intent(ctx, Terms.class);
                        break;
                    case R.id.wishlist:
                        i = (ctx instanceof Wishlist)? null : new Intent(ctx, Wishlist.class);
                        break;
                    case R.id.privacy:
                        i = (ctx instanceof Privacy)? null : new Intent(ctx, Privacy.class);
                        break;
                    case R.id.login:
                        i = (ctx instanceof LoginView)? null : new Intent(ctx, LoginView.class);
                        break;
                    case R.id.register:
                        i = (ctx instanceof SignupView)? null : new Intent(ctx, SignupView.class);
                        break;
                    case R.id.following:
                        i = (ctx instanceof Following)? null : new Intent(ctx, Following.class);
                        break;
                    case R.id.stores:
                        i = (ctx instanceof Stores)? null : new Intent(ctx, Stores.class);
                        break;
                    case R.id.logout:
                        i = (ctx instanceof LogoutView)? null : new Intent(ctx, LogoutView.class);
                        break;
                    case R.id.share:
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBodyText = "Hey there, Want to share some catalog!";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject here");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                        ctx.startActivity(Intent.createChooser(sharingIntent, "Shearing Option"));
                        break;
                }

                if (i != null) {
                    ctx.startActivity(i);
                    ((AppCompatActivity) ctx).finish();
                }

                return false;
            }
        });
    }

    static void showToast(Context ctx, String message){
        Toast.makeText(ctx,message,Toast.LENGTH_LONG).show();
    }
}
