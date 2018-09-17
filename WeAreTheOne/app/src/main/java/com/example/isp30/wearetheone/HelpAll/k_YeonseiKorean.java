package com.example.isp30.wearetheone.HelpAll;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.location.Location;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isp30.wearetheone.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class k_YeonseiKorean extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback {
    private Context context = null;
    private boolean m_bTrackingMode = true;
    private final TMapData.TMapPathType MAPTYPE = TMapData.TMapPathType.PEDESTRIAN_PATH;
    public TextView result;
    private static final String[] needPermissions = {
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
    };


    private TMapGpsManager tmapgps = null;
    private TMapView tMapView = null;
    private static String mApiKey = "2ff4a2a4-e298-49da-aec4-2e7efbfbd59e";
    private int mMarkID;
    private RecyclerView recyclerView;
    private Button show;
    private TMapPolyLine tpolyline = new TMapPolyLine();
    private FloatingActionButton floatingActionButton1,floatingActionButton2,floatingActionButton3,floatingActionButton4;


    private ArrayList<TMapPoint> m_tmapPoint = new ArrayList<TMapPoint>();
    private ArrayList<String> mArrayMarkerID = new ArrayList<String>();
    private ArrayList<MapPoint> m_mapPoint = new ArrayList<MapPoint>();
    private double[] latitudes = {37.5653121,37.5575584,37.5689278,37.5576784};
    private double[] longtitudes = {126.94352849999996,126.93667720000008,126.9842936,126.9215117};

    private double startLatitude;
    private double startLongtitude;
    private double endLatitude;
    private double endLontitude;
    private TMapPoint tMapPoint;
    private  TMapData tmapdata;


    private Intent intent;


    public void onLocationChange(Location location) {

        if(m_bTrackingMode){

            startLatitude = location.getLatitude();
            startLongtitude = location.getLongitude();

            tMapView.setLocationPoint(startLongtitude,startLatitude);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmap);
        intent = new Intent(getIntent());

        context = this;

        recyclerView = (RecyclerView)findViewById(R.id.academyInfo);
       /* show = (Button)findViewById(R.id.show);*/

        /*show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawline();
            }
        });
*/
        /*initData();*/
        result = (TextView)findViewById(R.id.result);





        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.mapView);
        tMapView = new TMapView(this);
        linearLayout.addView(tMapView);
        tMapView.setSKTMapApiKey(mApiKey);

        addPoint();
        showMarkerPoint();

        tMapView.setCompassMode(true);//현재 보는 방향
        tMapView.setIconVisibility(true);//현위치 아이콘 표시

        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);

        tmapgps = new TMapGpsManager(k_YeonseiKorean.this);
        tmapgps.setMinTime(1000);
        tmapgps.setMinDistance(5);

        TMapPoint tpoint = tMapView.getLocationPoint();
        double Latitude = tpoint.getLatitude();
        double Longitude = tpoint.getLongitude();




        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1); //위치권한 탐색 허용 관련 내용
            }
            return ;
        }
        tmapgps.setProvider(tmapgps.NETWORK_PROVIDER);//연결된 인터넷에서 현재 위치를 가져옴
        tmapgps.OpenGps();

        //화면중심을 단말의 현재위치로 이동
        tMapView.setTrackingMode(true);
        tMapView.setSightVisible(true);


        
        //풍선에서 우측 버튼 클릭시 할 행동입니다.
        tMapView.setOnCalloutRightButtonClickListener(new TMapView.OnCalloutRightButtonClickCallback() {
            @Override
            public void onCalloutRightButton(TMapMarkerItem tMapMarkerItem) {


            }
        });
        tMapView.setOnClickListenerCallBack(new TMapView.OnClickListenerCallback() {
            @Override
            public boolean onPressEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                for(int i = 0; i<arrayList.size();i++){
                   if(arrayList.get(i).getName().toString().equals("연세대학교 한국어학당"))
                   { Toast.makeText(k_YeonseiKorean.this, "눌렸습니다. 연세대학교가", Toast.LENGTH_SHORT).show();
                        endLatitude = latitudes[0];
                        endLontitude = longtitudes[0];
                        drawline();
                      /* intent = new Intent(Tmap.this,Academy_1.class);*/
                      }
                    if(arrayList.get(i).getName().toString().equals("베스트 프렌드 한국어 학원"))
                    { Toast.makeText(k_YeonseiKorean.this, "눌렸습니다. 베스트 프렌드 한국어 학원가", Toast.LENGTH_SHORT).show();
                        endLatitude = latitudes[1];
                        endLontitude = longtitudes[1];
                        drawline();
                       }
                    if(arrayList.get(i).getName().toString().equals("그린한국어학원"))
                    { Toast.makeText(k_YeonseiKorean.this, "눌렸습니다. 그린한국어학원가", Toast.LENGTH_SHORT).show();
                        endLatitude = latitudes[2];
                        endLontitude = longtitudes[2];
                        drawline();
                        }
                    if(arrayList.get(i).getName().toString().equals("가나다 한국어 어학원"))
                    { Toast.makeText(k_YeonseiKorean.this, "눌렸습니다. 가나다 한국어 어학원가", Toast.LENGTH_SHORT).show();
                        endLatitude = latitudes[3];
                        endLontitude = longtitudes[3];
                        drawline();
                        }
                   /* startActivity(intent);*/
                }
                return true;
            }

            @Override
            public boolean onPressUpEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                return false;
            }
        });
            floatingActionButton1 = (FloatingActionButton)findViewById(R.id.fa_btn_1);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endLatitude = latitudes[0];
                endLontitude = longtitudes[0];
                drawline();
            }
        });
        floatingActionButton2 = (FloatingActionButton)findViewById(R.id.fa_btn_2);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endLatitude = latitudes[1];
                endLontitude = longtitudes[1];
                drawline();
            }
        });
        floatingActionButton3 = (FloatingActionButton)findViewById(R.id.fa_btn_3);
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endLatitude = latitudes[2];
                endLontitude = longtitudes[2];
                drawline();
            }
        });
        floatingActionButton4 = (FloatingActionButton)findViewById(R.id.fa_btn_4);
        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endLatitude = latitudes[3];
                endLontitude = longtitudes[3];
                drawline();
            }
        });

    }
    public void addPoint(){
        m_mapPoint.add(new MapPoint("연세대학교 한국어학당",latitudes[0] ,longtitudes[0]));
        m_mapPoint.add(new MapPoint("베스트 프렌드 한국어 학원",latitudes[1] ,longtitudes[1]));
        m_mapPoint.add(new MapPoint("그린한국어학원",latitudes[2] ,longtitudes[2]));
        m_mapPoint.add(new MapPoint("가나다 한국어 어학원",latitudes[3] ,longtitudes[3]));


    }
    public void showMarkerPoint(){
        for(int i = 0; i < m_mapPoint.size(); i++){
            TMapPoint point = new TMapPoint(m_mapPoint.get(i).getLatitude()
            ,m_mapPoint.get(i).getLongitude());

            TMapMarkerItem item1 = new TMapMarkerItem();
            Bitmap bitmap = null;
            bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.pin);

            item1.setTMapPoint(point);
            item1.setName(m_mapPoint.get(i).getName());
            item1.setVisible(item1.VISIBLE);

            item1.setIcon(bitmap);

            bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.pin);

            item1.setCalloutTitle(m_mapPoint.get(i).getName());
            item1.setCalloutSubTitle("찾아가기");
            item1.setCanShowCallout(true);
            item1.setAutoCalloutVisible(true);

            Bitmap bitmap_i = BitmapFactory.decodeResource(context.getResources(),R.drawable.go);

            item1.setCalloutRightButtonImage(bitmap_i);

            String strID = String.format("pmarker%d",mMarkID++);

            tMapView.addMarkerItem(strID,item1);
            mArrayMarkerID.add(strID);
        }

    }
  /*  private void initData() {
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

    }*/

    private void drawline(){

        // Thread로 웹서버에 접속
        new Thread() {
            public void run() {

                TMapPoint startpoint2 = new TMapPoint(startLatitude,startLongtitude); //출발지
                TMapPoint endpoint2 = new TMapPoint(endLatitude,endLontitude); //목적지
                tmapdata = new TMapData();
                TMapPolyLine polyline = null;


                try {
                    polyline = tmapdata.findPathData(startpoint2, endpoint2);
                    polyline.setLineColor(Color.BLUE); //경로 스타일
                    polyline.setLineWidth(2); //경로 스타일
                    tMapView.addTMapPolyLine("polyline", polyline); //출발지와 목적지로 경로를 그립니다.


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                ArrayList arPoint = polyline.getLinePoint();
                TMapPoint result = (TMapPoint)arPoint.get(0);

                //출발지 마커로 확대하는 부분입니다.
                tMapView.setCenterPoint(result.getLongitude(), result.getLatitude());
                tMapView.setZoomLevel(19);
            }
        }.start();
    }







}
