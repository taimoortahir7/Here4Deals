package com.umairjabbar.here4deals;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    ListView listView;
    ArrayList prgmName;

    public static int [] prgmImages = {R.drawable.book, R.drawable.car, R.drawable.fashion, R.drawable.laptop, R.drawable.job};
    public static String [] prgmNameList = {"Books", "Cars", "Fashion", "Laptops", "Jobs"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blank, container, false);
        listView = (ListView)view.findViewById(R.id.home_list);
        listView.setAdapter(new AdapterForSubCatagory(getActivity(), prgmNameList, prgmImages));
        return view;
    }

}
