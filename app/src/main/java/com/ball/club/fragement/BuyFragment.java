package com.ball.club.fragement;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ball.club.ClothingActivity;
import com.ball.club.R;
import com.ball.club.adpater.CommonAdapter;
import com.ball.club.adpater.StoreAdapter;
import com.ball.club.adpater.ViewHolder;
import com.ball.club.bean.Menu;
import com.ball.club.bean.Store;
import com.ball.club.data.DBManger;
import com.ball.club.util.GlideImageLoader;
import com.ball.club.view.ObservableScrollView;
import com.ball.club.view.RoundCornerDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;


import java.util.ArrayList;
import java.util.List;


public class BuyFragment extends Fragment {//商家
    Banner banner;
    EditText followEdit;
    ImageView searchImage;
    ImageView add;
    RecyclerView listView_store;
    LinearLayout scrollView;
    ImageView ivAnimation;
    LinearLayout llAnimation;

    private List<Store> list_store;
    private List<Integer> list_img = new ArrayList<Integer>();
    StoreAdapter mStoreAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy, container, false);
        initView(view);
        initdata();
        list_store = DBManger.getInstance(getActivity()).getAllStore();
        listView_store.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        setContentCommonadapter();


        followEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    list_store = DBManger.getInstance(getActivity()).getStoreByKey(s.toString());
                    mStoreAdapter.updateAll(list_store);
                } else {
                    list_store = DBManger.getInstance(getActivity()).getAllStore();
                    mStoreAdapter.updateAll(list_store);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

    public void initView(View view){
        banner = view.findViewById(R.id.banner);
        followEdit = view.findViewById(R.id.follow_edit);
        searchImage = view.findViewById(R.id.search_image);
        add = view.findViewById(R.id.add);
        listView_store = view.findViewById(R.id.lv_store);
        scrollView = view.findViewById(R.id.scrollView);
        ivAnimation = view.findViewById(R.id.iv_animation);
        llAnimation = view.findViewById(R.id.ll_animation);
    }

    private void setContentCommonadapter() {
        mStoreAdapter = new StoreAdapter(getActivity(), list_store);

        listView_store.setAdapter(mStoreAdapter);
        mStoreAdapter.setOnItemClickListener(new StoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StoreAdapter.ViewHolder holder, Store store) {
                int index = Integer.parseInt(store.getIndex());
                Intent intent = new Intent(getContext(), ClothingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA", store);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };

    private void initdata() {
        list_img.add(R.mipmap.five);
        list_img.add(R.mipmap.one);
        list_img.add(R.mipmap.two);
        list_img.add(R.mipmap.three);
        list_img.add(R.mipmap.four);

        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(list_img);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //更新界面
                list_store = DBManger.getInstance(getActivity()).getAllStore();
                mStoreAdapter.updateAll(list_store);
            }
        });
    }
}

