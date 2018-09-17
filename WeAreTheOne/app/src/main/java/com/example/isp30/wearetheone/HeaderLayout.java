package com.example.isp30.wearetheone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HeaderLayout extends AppCompatActivity {
    private TextView name;
    private UserInfo userInfo = new UserInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_layout);




    }
}
