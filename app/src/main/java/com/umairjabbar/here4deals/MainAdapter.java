package com.umairjabbar.here4deals;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import static android.widget.Toast.*;

/**
 * Created by Umair Jabbar on 03/04/2017.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ArrayList mDataset;
    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater = null;

    public MainAdapter(Context MainActivity,ArrayList<String> mDataset, String[] prgmNameList, int[] prgmImages){
        this.mDataset=mDataset;
        result = prgmNameList;
        imageId = prgmImages;

        context = MainActivity;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view

        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_of_horizontal_recyclerview, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        holder.mTitle.setText((CharSequence) mDataset.get(position));
        View rowView;
        rowView = inflater.inflate(R.layout.row_of_horizontal_recyclerview, null);

        holder.textView.setText(result[position]);
        holder.imageView.setImageResource(imageId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, "Clicked" + result[position], LENGTH_LONG).show();
               MainActivity mn;
                Intent intent=new Intent(context,ItemDetailActivity.class);
                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTitle;
        TextView textView;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTitle=(TextView)itemView.findViewById(R.id.title);
            textView = (TextView) itemView.findViewById(R.id.textView_horizontal);
            imageView = (ImageView) itemView.findViewById(R.id.imageView_horizontal_cardview);
        }
    }

}
