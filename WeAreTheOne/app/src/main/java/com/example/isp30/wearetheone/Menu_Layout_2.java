package com.example.isp30.wearetheone;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Menu_Layout_2 extends Fragment {


    public Menu_Layout_2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu__layout_2,container,false);
        UserInfo userInfo = new UserInfo();
        TextView text_main = (TextView)v.findViewById(R.id.text_main2);
        text_main.setText(userInfo.getUserInfo());

        return v;
    }

}
