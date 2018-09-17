/*
package com.example.isp30.wearetheone.HelpAll;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isp30.wearetheone.Manifest;
import com.example.isp30.wearetheone.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class Location extends AppCompatActivity {
    private static final String MAP_BUNDLE_KEY = "MapBundleKey";
    private static final int REQUEST_USED_PERMISSION = 200;
    private int pinNumber = 1;
    private TextView routeTextView;

    private static final String[] needPermissions = {
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean permissionToLocationAccepted = true;

        switch (requestCode){
            case REQUEST_USED_PERMISSION:
                for(int result : grantResults){
                    if(result != PackageManager.PERMISSION_GRANTED){
                        permissionToLocationAccepted = true;
                        break;
                    }
                }
                break;

        }
        if(permissionToLocationAccepted == false){
            finish();
        }
        else{
            getMyLocation();//권한 획득 후 내위치로 이동
        }
    }
    private static final LatLng DEFAULT_LOCATION
            = new LatLng(37.56641923090,126.9778741551);
    private static final int DEFAULT_ZOOM = 15; //지도 확대 값
    private MapView mapView;
    private GoogleMap map; //GoogleMap 객체를 저장할 변수 선언
    private android.location.Location lastKnownLocation;

    private static final long INTERVAL_TIME = 5000;
    private static final long FATEST_INTERVAL_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for(String permission : needPermissions){
            if(ActivityCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,needPermissions,REQUEST_USED_PERMISSION);
                break;
            }
        }
        setContentView(R.layout.help_academy_location);
        routeTextView = (TextView)findViewById(R.id.route);
        Intent intent = new Intent(getIntent());

        Bundle mapViewBundle = null;//저장된 MapView 상태 정보를 담을 Bundle 변수
        if(savedInstanceState != null){
            mapViewBundle = savedInstanceState.getBundle(MAP_BUNDLE_KEY);
        }

        mapView = (MapView)findViewById(R.id.map);
        mapView.onCreate(mapViewBundle);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {//MapView 준비 콜백 설정
                map = googleMap;

                LatLng seoul = new LatLng(37.56641923090,126.9778741551);
                MarkerOptions markerOptions = new MarkerOptions();//마커의 옵션 객체 생성
                markerOptions.position(seoul);
                markerOptions.title("서울");

                map.addMarker(markerOptions);

                map.moveCamera(CameraUpdateFactory.newLatLng(seoul)); //지도를 마커 위치로 이동

                if(ActivityCompat.checkSelfPermission(Location.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(Location.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                    return;
                }
                map.setMyLocationEnabled(true);//내 위치 마커 표시

                map.getUiSettings().setMyLocationButtonEnabled(true);//내 위치로 이동 버튼 표시

                getMyLocation();

            }
        });
    }
    private void getMyLocation() {
        if(ActivityCompat.checkSelfPermission(Location.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(Location.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            return;
        }
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);

        FusedLocationProviderClient fusedLocationProviderClient = new FusedLocationProviderClient(this);

        Task<android.location.Location> task = fusedLocationProviderClient.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener<android.location.Location>() {
            @Override
            public void onSuccess(android.location.Location location) {
                lastKnownLocation = location;

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastKnownLocation.getLatitude(),lastKnownLocation.getLongitude()),DEFAULT_ZOOM));

                updateMyLocation();
                setMapLongClickEvent();
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(ActivityCompat.checkSelfPermission(Location.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(Location.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                    return;
                }
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION,DEFAULT_ZOOM));//지도를 기본 위치로 이동
                setMapLongClickEvent();
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);//내 위치 펴시 관련 ui 제거
            }
        });

    }

    private void setMapLongClickEvent() {
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Toast.makeText(Location.this, "위도 "+latLng.latitude+"경도"+latLng.longitude, Toast.LENGTH_SHORT).show();

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("대사관"+pinNumber);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.point));

                map.addMarker(markerOptions);
                pinNumber +=1;

                getRoute(latLng);
            }
        });
    }

    private void getRoute(final LatLng endPoint) {
        if(ActivityCompat.checkSelfPermission(Location.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(Location.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            return;
        }
        FusedLocationProviderClient fusedLocationProviderClient
                = new FusedLocationProviderClient(this);

        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<android.location.Location>() {
            @Override
            public void onSuccess(android.location.Location location) {
                android.location.Location lastLocation = location;
                LatLng startPoint = new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());

                GoogleRouteApi googleRouteApi = new GoogleRouteApi(Location.this, startPoint, endPoint, new GoogleRouteApi.EventListener() {
                    @Override
                    public void onApiResult(String result) {
                        Toast.makeText(Location.this, "요청 성공 :"+result, Toast.LENGTH_SHORT).show();

                        Route route = GoogleRouteResultParser.getRoute(result);
                        showRoute(route);
                    }

                    @Override
                    public void onApiFailed() {
                        Toast.makeText(Location.this, "요청을 실패하였습니다. ", Toast.LENGTH_SHORT).show();
                    }
                });
                googleRouteApi.start();
            }
        });

    }

    private void showRoute(Route route) {
        if(route != null){

            routeTextView.setText("");
            routeTextView.setText("총 걸리는 시간은 "+route.routeTime);

            for(Step step: route.steps){
                routeTextView.append("\n");
                routeTextView.append(step.stepText);

                if(step.transitName != null){
                    routeTextView.append("\n");
                    routeTextView.append(step.transitName);

                    if(step.transitStopNumber>0){
                        routeTextView.append("숭차 후 "+step.transitStopNumber+"개 정류소에서 하차");
                    }else{
                        routeTextView.append("승차");
                    }
                }
            }
        }
    }

    private void updateMyLocation() {
        if(ActivityCompat.checkSelfPermission(Location.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(Location.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            return;
        }
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);//업데이트의 정확도 생성
        locationRequest.setInterval(INTERVAL_TIME);//업데이트 주기 생성
        locationRequest.setFastestInterval(FATEST_INTERVAL_TIME);//가장 빠른 업데이트 주기

        FusedLocationProviderClient fusedLocationProviderClient = new FusedLocationProviderClient(this);

        fusedLocationProviderClient.requestLocationUpdates(locationRequest,new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                android.location.Location location = locationResult.getLastLocation();

                map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(),location.getLongitude())));
            }
        },null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        Bundle mapBundle = outState.getBundle(MAP_BUNDLE_KEY);//이미 저장된 MapView 상태 정보가 있는지 확인

        if(mapBundle == null){//이미 저장된 데이터가 없는 경우
            mapBundle = new Bundle();//새로운 상태 정보 객체 생성
            outState.putBundle(MAP_BUNDLE_KEY,mapBundle);//액티비티 상태정보에 MapView 상태 정보 객체 저장
        }
        mapView.onSaveInstanceState(mapBundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}

*/
