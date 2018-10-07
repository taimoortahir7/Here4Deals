package com.umairjabbar.here4deals;

/**
 * Created by Umair Jabbar on 02/04/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sarem on 4/1/2017.
 */

public class AdapterForSubCatagory extends BaseAdapter {

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater = null;
    public AdapterForSubCatagory(Context mainActivity, String[] prgmNameList, int[] prgmImages){

        result = prgmNameList;
        context = mainActivity;
        imageId = prgmImages;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }


    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class Holder {

        TextView textView;
        ImageView imageView;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.row, null);
        holder.textView = (TextView) rowView.findViewById(R.id.textView1);
        holder.imageView = (ImageView) rowView.findViewById(R.id.imageView);
        holder.textView.setText(result[position]);
        holder.imageView.setImageResource(imageId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Clicked" + result[position], Toast.LENGTH_LONG).show();
                MainActivity mn;
                Intent intent=new Intent(context,ItemDetailActivity.class);
                context.startActivity(intent);
            }
        });
        return rowView;
    }
}