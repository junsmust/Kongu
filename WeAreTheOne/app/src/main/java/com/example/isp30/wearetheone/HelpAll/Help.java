package com.example.isp30.wearetheone.HelpAll;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.isp30.wearetheone.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Help extends Fragment {
    private HelpAdapter helpAdapter;
    private ListView mListView;
    private List<HelpItem> helpList;


    public Help() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.help_main,container,false);

        mListView = (ListView)v.findViewById(R.id.helpList);

        helpList = new ArrayList<HelpItem>();

        helpList.add(new HelpItem("여권을 잃어버렸어요","어디서 찾나요?"));
        helpList.add(new HelpItem("한국어를 배우고싶어요","어디서 찾나요?"));
        helpList.add(new HelpItem("대사관을 가고 싶어요","어떻게 가나요?"));
        helpList.add(new HelpItem("여권을 잃어버렸어요","어디서 찾나요?"));
        helpList.add(new HelpItem("여권을 잃어버렸어요","어디서 찾나요?"));
        helpList.add(new HelpItem("여권을 잃어버렸어요","어디서 찾나요?"));
        helpList.add(new HelpItem("여권을 잃어버렸어요","어디서 찾나요?"));
        helpList.add(new HelpItem("여권을 잃어버렸어요","어디서 찾나요?"));
        helpList.add(new HelpItem("여권을 잃어버렸어요","어디서 찾나요?"));
        helpList.add(new HelpItem("여권을 잃어버렸어요","어디서 찾나요?"));
        helpList.add(new HelpItem("여권을 잃어버렸어요","어디서 찾나요?"));
        helpList.add(new HelpItem("여권을 잃어버렸어요","어디서 찾나요?"));

        helpAdapter = new HelpAdapter(getContext(),helpList);
        mListView.setAdapter(helpAdapter);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(helpList.get(position).getTitle().equals("여권을 잃어버렸어요")){
                    getFragmentManager().beginTransaction().replace(R.id.frame_container,new Help_look_Academy()).addToBackStack(null).commit();
                }

                    if(helpList.get(position).getTitle().equals("대사관을 가고 싶어요")){
                    getFragmentManager().beginTransaction().replace(R.id.frame_container,new Help_Look_Embassy()).addToBackStack(null).commit();
                }
                if(helpList.get(position).getTitle().equals("한국어를 배우고싶어요")){
                    getFragmentManager().beginTransaction().replace(R.id.frame_container,new Help_look_Academy()).addToBackStack(null).commit();
                  /*  Intent intent = new Intent(getContext(), k_YeonseiKorean.class);
                    startActivity(intent);*/
                }
            }
        });
    }

}
