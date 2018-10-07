package com.umairjabbar.here4deals;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;


import com.umairjabbar.here4deals.DrawerMenuClasses.Wishlist;

import java.util.ArrayList;

/**
 * Created by Umair Jabbar on 03/04/2017.
 */

class MainCapsuleAdapter extends RecyclerView.Adapter<MainCapsuleAdapter.ViewHolder> {
    private ArrayList mDataset;
    String [] result;
    Context context;

    public MainCapsuleAdapter(Context ctx, String[] prgmNameList){
        this.result=prgmNameList;
        context = ctx;
    }
    @Override
    public MainCapsuleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view

        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_for_capsule, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return (vh);
    }

    @Override
    public void onBindViewHolder(final MainCapsuleAdapter.ViewHolder holder, final int position) {
        //holder.mTitle.setText((CharSequence) mDataset.get(position));
        holder.mTitle.setText(result[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mTitle.getText().toString().equalsIgnoreCase(result[position])) {
                    Intent intent = new Intent(context, Wishlist.class);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "" + result[position], Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return result.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            mTitle=(TextView)itemView.findViewById(R.id.title_capsule);
        }
    }
}
