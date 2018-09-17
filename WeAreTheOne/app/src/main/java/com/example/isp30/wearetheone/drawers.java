/*
package com.example.isp30.wearetheone;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class drawers extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ListView listView = null;
    UserInfo userInfo = new UserInfo();
      TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawers);
        Intent intent = new Intent(getIntent());



        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayoutNe);
        ActionBarDrawe*/
/**//*
rToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View nav_header_view = navigationView.getHeaderView(0);

        TextView user_name = (TextView)nav_header_view.findViewById(R.id.user_name);
        user_name.setText(userInfo.getUserInfo());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayoutNew);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer,menu);
        return true;

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.first_navigation_item){
            Toast.makeText(this, "1번 눌림", Toast.LENGTH_SHORT).show();
           */
/* UserInfo userInfo = new UserInfo();
            TextView textView = (TextView)findViewById(R.id.user_name);
            textView.setText(userInfo.getUserInfo());*//*

        }else if(id == R.id.second_navigation_item){
            Toast.makeText(this, "2번 메뉴 눌림", Toast.LENGTH_SHORT).show();
        }else if(id== R.id.third_navigation_item){
            Toast.makeText(this, "3번 메뉴 눌림", Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayoutNew);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}
*/
