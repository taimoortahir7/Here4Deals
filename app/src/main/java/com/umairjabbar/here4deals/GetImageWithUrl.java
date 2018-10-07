package com.umairjabbar.here4deals;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Rana Tayyab on 25-Mar-17.
 */

public class GetImageWithUrl extends AsyncTask<String,Void, Bitmap> {
    ImageView mImageView;

    public GetImageWithUrl(ImageView imageView){
        mImageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap image = null;

        try{
            InputStream in  = new URL(url).openStream();
            image = BitmapFactory.decodeStream(in);
        } catch (Exception e){
            Log.e("Error",e.getMessage());
            e.printStackTrace();
        }

        return image;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        mImageView.setImageBitmap(result);
    }

}
