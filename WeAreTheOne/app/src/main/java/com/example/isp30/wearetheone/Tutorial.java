package com.example.isp30.wearetheone;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class Tutorial extends AppCompatActivity implements ViewFlipperAction.ViewFlipperCallback  {
    ViewFlipper flipper;
    List<ImageView> indexes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        flipper = (ViewFlipper)findViewById(R.id.flipper);
        ImageView index0 = (ImageView)findViewById(R.id.imgIndex0);
        ImageView index1 = (ImageView)findViewById(R.id.imgIndex1);
        ImageView index2 =  (ImageView)findViewById(R.id.imgIndex2);

        indexes = new ArrayList<>();
        indexes.add(index0);
        indexes.add(index1);
        indexes.add(index2);



        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.fragment_tutorial_1,flipper,false);
        View view2 = inflater.inflate(R.layout.fragment_tutorial_2,flipper,false);
        View view3 = inflater.inflate(R.layout.fragment_tutorial_3,flipper,false);

        flipper.addView(view1);
        flipper.addView(view2);
        flipper.addView(view3);

        flipper.setOnTouchListener(new ViewFlipperAction(this,flipper));

    }


    @Override
    public void onFlipperActionCallback(int position) {
        Log.d("ddd", ""+position);
        for(int i=0; i<indexes.size(); i++){
            ImageView index = indexes.get(i);
            //현재화면의 인덱스 위치면 녹색
            if(i == position){
                index.setBackgroundColor(Color.RED);
            }
            //그외
            else{
                index.setBackgroundColor(Color.WHITE);
            }
        }



    }
}
