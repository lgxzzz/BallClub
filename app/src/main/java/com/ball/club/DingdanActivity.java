package com.ball.club;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ball.club.adpater.DingdanAdapter;
import com.ball.club.bean.Store;
import com.ball.club.bean.StoreDingDan;
import com.ball.club.bean.User;
import com.ball.club.data.DBManger;
import com.ball.club.view.RoundCornerDialog;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class DingdanActivity extends AppCompatActivity {
    TextView tvOrder;
    RecyclerView lvStore;
    List<StoreDingDan> list_store;

    DingdanAdapter recyclerViewCollAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);
        initView();
        initData();
    }

    private void setContentCommonadapter() {
        recyclerViewCollAdapter = new DingdanAdapter(this, list_store);
        lvStore.setAdapter(recyclerViewCollAdapter);
        recyclerViewCollAdapter.setOnItemClickListener(new DingdanAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DingdanAdapter.ViewHolder holder, StoreDingDan store) {

                Store store1=new Store();
                store1.setId(Integer.parseInt(store.getStore_id()));
                store1.setName(store.getName());
                store1.setType(store.getType());
                store1.setpicture(store.getpicture());
                store1.setprice(store.getprice());
                store1.setBianhao(store.getBianhao());
                store1.setMoney(store.getMoney());
                Intent intent = new Intent(DingdanActivity.this, ClothingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA", store1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void initView(){
        tvOrder = findViewById(R.id.tv_order);
        lvStore = findViewById(R.id.lv_dingdan);
    }

    public void initData(){
        User mUser = DBManger.getInstance(this).mUser;
        list_store = DBManger.getInstance(this).getAllStoreDingDan(mUser.getUserId());
        lvStore.setLayoutManager(new GridLayoutManager(this, 2));
        setContentCommonadapter();
    }

    public void refreshData(){
        User mUser = DBManger.getInstance(this).mUser;
        list_store = DBManger.getInstance(this).getAllStoreDingDan(mUser.getUserId());
        recyclerViewCollAdapter.updateAll(list_store);
    }
}
