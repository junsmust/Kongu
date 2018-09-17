package com.example.isp30.wearetheone.TripAll;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.isp30.wearetheone.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Trip_all_menu extends Fragment {
    private Button ep, jr, dhr, scd,gbsb,nw,sc,mp,ghm,jg,ddm,jrgj,sd,hy,md
            ,gd,sh,ys,itw,gs,ydp,dj,scho,garosu,kangnam,songpa,kangdong,seouluni,guro;


    public Trip_all_menu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trip_all_menu,container,false);

        ep = (Button)v.findViewById(R.id.ep);
        ep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),TripEnp.class);
                startActivity(intent);
            }
        });
        return v;
    }


}
