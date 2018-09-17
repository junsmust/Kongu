package com.example.isp30.wearetheone.HelpAll;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.isp30.wearetheone.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Help_Look_Embassy extends Fragment {
    private Help_Embassy_Adapter help_embassy_adapter;
    private ListView listView;
    private List<Help_Embassy_item> listItm;

    public Help_Look_Embassy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.help_lookembassy,container,false);


        listView = v.findViewById(R.id.embassyList);

        listItm = new ArrayList<Help_Embassy_item>();

        listItm.add(new Help_Embassy_item(R.drawable.china,"Chinese Embassy"));
        listItm.add(new Help_Embassy_item(R.drawable.china,"Chinese Embassy"));
        listItm.add(new Help_Embassy_item(R.drawable.china,"Chinese Embassy"));
        listItm.add(new Help_Embassy_item(R.drawable.china,"Chinese Embassy"));
        listItm.add(new Help_Embassy_item(R.drawable.china,"Chinese Embassy"));
        listItm.add(new Help_Embassy_item(R.drawable.china,"Chinese Embassy"));
        listItm.add(new Help_Embassy_item(R.drawable.china,"Chinese Embassy"));
        listItm.add(new Help_Embassy_item(R.drawable.china,"Chinese Embassy"));
        listItm.add(new Help_Embassy_item(R.drawable.china,"Chinese Embassy"));
        listItm.add(new Help_Embassy_item(R.drawable.china,"Chinese Embassy"));
        listItm.add(new Help_Embassy_item(R.drawable.china,"Chinese Embassy"));

        Help_Embassy_Adapter adapter = new Help_Embassy_Adapter(getContext(),listItm);
        listView.setAdapter(adapter);

        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

           }
       });
    }
}
