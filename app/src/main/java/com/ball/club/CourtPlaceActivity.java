package com.ball.club;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.ball.club.bean.Court;
import com.ball.club.view.TitleView;

/***
 *地图显示电梯位置信息activity
 *
 *
 * **/
public class CourtPlaceActivity extends Activity {
    private MapView mMapView = null;
    private AMap mAMap;
    private TitleView mTitleView;
    private Marker mLocationMarker; // 选择的点
    private Court mCourt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_court_place);
        initView(savedInstanceState);
        initData();
    }

    public void initView(Bundle savedInstanceState){
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        mAMap = mMapView.getMap();
//        mUiSettings = mAMap.getUiSettings();
//        mUiSettings.setZoomControlsEnabled(false); //隐藏缩放控件
        mTitleView = findViewById(R.id.title_view);
        mTitleView.setTitle("球场位置");
        mTitleView.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    };

    public void initData(){
        mCourt = (Court) getIntent().getExtras().getSerializable("DATA");
        String address = mCourt.getPosition();
        String[] params= address.split(",");
        double lat = Double.parseDouble(params[1]);
        double lon = Double.parseDouble(params[0]);
        addmark(lat,lon);
    };

    //增加图标
    private void addmark(double latitude, double longitude) {
        mAMap.clear();
        mLocationMarker = mAMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.drawable.navi_icon_1)))
                .draggable(true));
        LatLng mPosition = mLocationMarker.getPosition();
        mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mPosition.latitude, mPosition.longitude), 18));
    }

}
