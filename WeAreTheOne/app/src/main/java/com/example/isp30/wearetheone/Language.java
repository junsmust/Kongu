package com.example.isp30.wearetheone;

import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;
import java.util.List;

public class Language extends Fragment {
    private RecyclerView recyclerView;
    public Language(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_language,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView2);
        initData();

        return v;

    }
    //데이터 초기화
    private void initData() {
        List<KoreanItems> item = new ArrayList<KoreanItems>();

        
            item.add(new KoreanItems(R.drawable.common,"일반적인 대화"));
            item.add(new KoreanItems(R.drawable.dating,"데이트 할 때"));
            item.add(new KoreanItems(R.drawable.drink,"술마실 때"));
            item.add(new KoreanItems(R.drawable.angry,"화났을 때"));



        recyclerView.setAdapter(new KoreanAdapters(item,R.layout.koreanitems));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

}


