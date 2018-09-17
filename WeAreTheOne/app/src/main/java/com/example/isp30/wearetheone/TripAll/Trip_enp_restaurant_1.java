package com.example.isp30.wearetheone.TripAll;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.isp30.wearetheone.R;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class Trip_enp_restaurant_1 extends AppCompatActivity {
    private RatingBar ratingBar;
    private TextView ratingResult;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_enp_restaurant_1);
        Intent intent = new Intent(getIntent());

        final DBRaiting dbRaiting = new DBRaiting(getApplicationContext(),"Raiting.db",null,1);

        ratingResult = (TextView)findViewById(R.id.ratingResult);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                final  int numStars = ratingBar.getNumStars();
                    ratingResult.setText(rating+"/"+numStars);
            }
        });
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        MyRecyclerAdapter adapter = new MyRecyclerAdapter(this,createItemList(),R.layout.recycltem);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private List<TripItem> createItemList(){
        List<TripItem> items = new ArrayList<TripItem>();

        items.add(new TripItem(getDrawable(R.drawable.ic_book_black_24dp),"예약문의","031-745-7325"));
        items.add(new TripItem(getDrawable(R.drawable.ic_restaurant_menu_black_24dp),"메뉴판 보기","메뉴판"));
        items.add(new TripItem(getDrawable(R.drawable.ic_location_on_black_24dp),"서울특별시 중구 명동2가 83-5","지도 보기"));
        items.add(new TripItem(getDrawable(R.drawable.ic_access_time_black_24dp),"영업시간","영업시간 보기"));
        items.add(new TripItem(getDrawable(R.drawable.ic_local_parking_black_24dp),"주차 여부","가능"));

        return items;
    }
}
