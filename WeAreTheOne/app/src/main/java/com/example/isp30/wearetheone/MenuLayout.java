package com.example.isp30.wearetheone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isp30.wearetheone.HelpAll.Help;
import com.example.isp30.wearetheone.TripAll.Trip_all_menu;

public class MenuLayout extends AppCompatActivity  {
    private Intent intent;
    private Button info, solu, comu, trip;
    private android.support.v4.app.FragmentManager fm;
    private FragmentTransaction ft;
    private FloatingActionButton home;
    private RelativeLayout relativeLayout;
    private ListView listView = null;
    private DrawerLayout drawerLayout;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    UserInfo userInfo = new UserInfo();
    TextView textView;
    private ImageButton menu;
   private final String[] items = {"회원정보 ","한국 생활이란 ","도움말","세팅"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_layout);
        UserInfo userInfo = new UserInfo();

        intent = new Intent(getIntent());
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(userInfo.getUserInfo()+"님");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);

        listView = (ListView)findViewById(R.id.drawer_menulist);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(MenuLayout.this, "회원정보가 눌렸습니다.", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MenuLayout.this, "한국 생활이란", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MenuLayout.this, "도움말", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(MenuLayout.this, "세팅", Toast.LENGTH_SHORT).show();
                        break;
                }
                DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });


/*
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View nav_header_view = navigationView.getHeaderView(0);

        TextView user_name = (TextView)nav_header_view.findViewById(R.id.user_name);
        user_name.setText(userInfo.getUserInfo());*/

       /* toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbarInclude);
        setSupportActionBar(toolbar);

        menu = (ImageButton)toolbar.findViewById(R.id.menu);
        menu.setOnClickListener(onClickListener);
        
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = setUpActionBarToggle();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        
        navigationView = (NavigationView)findViewById(R.id.navigationView);
        setUpDrawerContent(navigationView);
*/

        info = (Button) findViewById(R.id.infoBtn);
        solu = (Button) findViewById(R.id.helpBtn);
        comu = (Button) findViewById(R.id.commuBtn);
        trip = (Button) findViewById(R.id.tripBtn);
        home = (FloatingActionButton)findViewById(R.id.home);
        relativeLayout = (RelativeLayout)findViewById(R.id.frame_container);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.notice);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.GONE);
                info.setBackgroundColor(getResources().getColor(R.color.buttonPressed));
                solu.setBackgroundColor(getResources().getColor(R.color.mainPink));
                comu.setBackgroundColor(getResources().getColor(R.color.mainPink));
                trip.setBackgroundColor(getResources().getColor(R.color.mainPink));

                fm = getSupportFragmentManager();

                ft = fm.beginTransaction().replace(R.id.frame_container, new Language());
                ft.commit();
              /*  Intent intent = new Intent(MenuLayout.this,Language.class);
                startActivity(intent);*/
            }
        });

        solu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.GONE);
                info.setBackgroundColor(getResources().getColor(R.color.mainPink));
                solu.setBackgroundColor(getResources().getColor(R.color.buttonPressed));
                comu.setBackgroundColor(getResources().getColor(R.color.mainPink));
                trip.setBackgroundColor(getResources().getColor(R.color.mainPink));

                fm = getSupportFragmentManager();
                ft = fm.beginTransaction().replace(R.id.frame_container, new Help());
                ft.commit();
            }
        });
        comu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.GONE);
                info.setBackgroundColor(getResources().getColor(R.color.mainPink));
                solu.setBackgroundColor(getResources().getColor(R.color.mainPink));
                comu.setBackgroundColor(getResources().getColor(R.color.buttonPressed));
                trip.setBackgroundColor(getResources().getColor(R.color.mainPink));

                /*Intent intent = new Intent(MenuLayout.this,drawers.class);
                startActivity(intent);*/
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction().replace(R.id.frame_container, new Community());
                ft.commit();
            }
        });

        trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.GONE);
                info.setBackgroundColor(getResources().getColor(R.color.mainPink));
                solu.setBackgroundColor(getResources().getColor(R.color.mainPink));
                comu.setBackgroundColor(getResources().getColor(R.color.mainPink));
                trip.setBackgroundColor(getResources().getColor(R.color.buttonPressed));

                fm = getSupportFragmentManager();
                ft = fm.beginTransaction().replace(R.id.frame_container, new Trip_all_menu());
                ft.commit();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {fm = getSupportFragmentManager();
                ft = fm.beginTransaction().replace(R.id.frame_container, new Menu_Layout_2());
                ft.commit();

            }
        });
      /*  DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        drawerLayout.closeDrawer(Gravity.LEFT);*/
    }



   /* @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.menu:
                    drawerLayout.openDrawer(GravityCompat.START);
                    break;

            }
        }
    };

    private void setUpDrawerContent(NavigationView navigationView) {
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.first_navigation_item:
                    Toast.makeText(MenuLayout.this, "첫번째 Navigation item 입니다. ", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.second_navigation_item:
                    Toast.makeText(MenuLayout.this, "두번째 Navigation Item 입니다.", Toast.LENGTH_SHORT).show();
                    break;
            }
          
            return false;
        }
    });
    }
    private ActionBarDrawerToggle setUpActionBarToggle() {
        return new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
    }*/
  /* @Override
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
           *//* UserInfo userInfo = new UserInfo();
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
    }*/
}
