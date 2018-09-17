package com.example.isp30.wearetheone.TripAll;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.isp30.wearetheone.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Trip_enp_restaurant extends Fragment {
    private Button next;
    private LinearLayout previous;
    private TripAdapter restaurantAdapter;
    private ListView restaurant_list_view;
    private List<TripItem> restaurant_List;

    public Trip_enp_restaurant() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_trip_enp_restaurant,container,false);

        restaurant_list_view = (ListView) v.findViewById(R.id.restaurant_list);

        restaurant_List = new ArrayList<TripItem>();

        restaurant_List.add(new TripItem(getContext().getDrawable(R.drawable.namsan),"새마을식당","여기는 남산이빈다. "));
        restaurant_List.add(new TripItem(getContext().getDrawable(R.drawable.namsan),"동마르 식당.","여기는 남산이빈다. "));
        restaurant_List.add(new TripItem(getContext().getDrawable(R.drawable.namsan),"새마을 식당.","여기는 남산이빈다. "));
        restaurant_List.add(new TripItem(getContext().getDrawable(R.drawable.namsan),"동마르 식당.","여기는 남산이빈다. "));
        restaurant_List.add(new TripItem(getContext().getDrawable(R.drawable.namsan),"동마르 식당.","여기는 남산이빈다. "));
        restaurant_List.add(new TripItem(getContext().getDrawable(R.drawable.namsan),"동마르 식당.","여기는 남산이빈다. "));
        restaurant_List.add(new TripItem(getContext().getDrawable(R.drawable.namsan),"동마르 식당.","여기는 남산이빈다. "));

        restaurantAdapter = new TripAdapter(getContext(),restaurant_List);
        restaurant_list_view.setAdapter(restaurantAdapter);


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        restaurant_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (restaurant_List.get(position).getTitle()){
                    case "동마르 식당.":
                        Intent intent = new Intent(getActivity(),Trip_enp_restaurant_1.class);
                        startActivity(intent);
                        break;
                }
            }
        });

    }

}
