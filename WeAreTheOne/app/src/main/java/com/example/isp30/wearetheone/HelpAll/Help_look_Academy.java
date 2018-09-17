package com.example.isp30.wearetheone.HelpAll;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.isp30.wearetheone.R;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class Help_look_Academy extends Fragment {

   /* private Button bt1,bt2,bt3,bt4;*/
   private RecyclerView recyclerView;
    public Help_look_Academy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.help_lookacademy,container,false);
        recyclerView = (RecyclerView)v.findViewById(R.id.academyInfo);
        initData();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

      /*  bt1 = (Button)getView().findViewById(R.id.bt1);
        bt2 = (Button)getView().findViewById(R.id.bt2);
        bt3 = (Button)getView().findViewById(R.id.bt3);
        bt4 = (Button)getView().findViewById(R.id.bt4);

        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Tmap.class);
                startActivity(intent);
            }
        });*/
    }
    private void initData() {
        List<AcademiesItems> item = new ArrayList<AcademiesItems>();


        item.add(new AcademiesItems(R.drawable.ic_lock_open_black_24dp,"연세대학교 한국어학당"));
        item.add(new AcademiesItems(R.drawable.ic_lock_open_black_24dp,"베스트 프렌드 한국어학원"));
        item.add(new AcademiesItems(R.drawable.ic_lock_open_black_24dp,"그린 한국어 학원"));
        item.add(new AcademiesItems(R.drawable.ic_lock_open_black_24dp,"가나다 한국어 학원"));
        item.add(new AcademiesItems(R.drawable.ic_lock_open_black_24dp,"마바사 한국어 학원"));
        item.add(new AcademiesItems(R.drawable.ic_lock_open_black_24dp,"아자차 한국어 학원"));
        item.add(new AcademiesItems(R.drawable.ic_lock_open_black_24dp,"타파하 한국어 학원"));
        item.add(new AcademiesItems(R.drawable.ic_lock_open_black_24dp,"오이잉 한국어 학원"));
        item.add(new AcademiesItems(R.drawable.ic_lock_open_black_24dp,"수아디 한국어 학원"));

        recyclerView.setAdapter(new AcademiesAdapter(item,R.layout.academyitems));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
