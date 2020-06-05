package com.ball.club.fragement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.amap.api.location.AMapLocation;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.ball.club.R;
import com.ball.club.adpater.CourtAdapter;
import com.ball.club.adpater.NewsAdapter;
import com.ball.club.bean.Court;
import com.ball.club.bean.News;
import com.ball.club.navi.LocationMgr;
import com.ball.club.navi.PoiSearchMgr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NearFragment extends Fragment {

    //搜索模块
    private PoiSearchMgr mPoiSearchMgr;
    //定位模块
    private LocationMgr mLocationMgr;

    public LatLng mCurrentPosition; //当前地点

    public Double mLongitude;
    public Double mLatitude;
    private String mKeyWord="球场";

    //周边球场数据
    public List<Court> mCourt = new ArrayList<>();

    ListView mMsgListview;

    CourtAdapter mCourtAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragement_near, container, false);
        initView(view);
        return view;
    }

    public static NearFragment getInstance() {
        return new NearFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public void initView(View view){
        mMsgListview = view.findViewById(R.id.search_info_list);
    };

    public void initData(){
        mLocationMgr  = new LocationMgr(getContext());
        mPoiSearchMgr = new PoiSearchMgr(getContext());
        mPoiSearchMgr.setPoiListener(new PoiSearchMgr.PoiSearchListener() {

            @Override
            public void onSuccess(List<PoiItem> poiItems) {

                for (int i = 0;i<poiItems.size();i++){
                    PoiItem item = poiItems.get(i);
                    //生成电梯数据
                    LatLonPoint latLonPoint = item.getLatLonPoint();
                    Court court = new Court();
                    court.setName(item.getSnippet());
                    court.setPosition(latLonPoint.getLongitude()+","+latLonPoint.getLatitude());
                    Random random = new Random();
                    int num = random.nextInt(20);
                    court.setNumber(num+"");
                    mCourt.add(court);
                }

                mCourtAdapter = new CourtAdapter(getContext(),mCourt);
                mMsgListview.setAdapter(mCourtAdapter);
            }

            @Override
            public void onFail(String error) {

            }
        });
        getPosition();
    };

    public void doSearchQueryWithKeyWord(){
        mPoiSearchMgr.doSearchQuery(mKeyWord,mCurrentPosition.latitude,mCurrentPosition.longitude);
    }

    //获取定位信息并且查询当前的POI点周边
    public void getPosition(){
        mLocationMgr.getLonLat(getContext(), new LocationMgr.LonLatListener() {
            @Override
            public void getLonLat(AMapLocation aMapLocation) {
                mLongitude = aMapLocation.getLongitude();
                mLatitude = aMapLocation.getLatitude();
                mCurrentPosition = new LatLng(mLatitude,mLongitude);
                doSearchQueryWithKeyWord();
            }
        });
    }
}
