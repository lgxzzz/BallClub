package com.ball.club;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ball.club.bean.Store;
import com.ball.club.bean.StoreDingDan;
import com.ball.club.bean.User;
import com.ball.club.data.DBManger;
import com.bumptech.glide.Glide;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CollectionActivity extends AppCompatActivity {

    TextView goodname;
    TextView guige;
    TextView dianPrice;
    TextView tuanPrice;
    ImageView goodPicture;
    TextView detail;
    TextView ccc;
    TextView tvSelected;
    TextView bbb;
    TextView tvTotal;
    ImageView ivCollect1;
    ImageView ivCollect2;
    Button btnTakeOrder;
    LinearLayout bottomLayout;
    Store store;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        user = DBManger.getInstance(this).mUser;
        store = (Store) getIntent().getSerializableExtra("DATA");
        goodname.setText(store.getName());
        guige.setText("1份/" + store.getMoney());
        dianPrice.setText("￥" + store.getMoney());
        tuanPrice.setText("￥" + store.getprice());
        Glide.with(CollectionActivity.this).load(store.getpicture()).into(goodPicture);
        ccc.setText("￥" + store.getprice());
        bbb.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        bbb.setText("￥" + store.getMoney());
//        CollectionBean collect = DbSqliteHelper.getInstance(CollectionActivity.this).findColl(store.getId()+"", user);
//        if (!TextUtils.isEmpty(collect.getName())){
//            ivCollect2.setVisibility(View.VISIBLE);
//            ivCollect1.setVisibility(View.GONE);
//        }else {
//            ivCollect2.setVisibility(View.GONE);
//            ivCollect1.setVisibility(View.VISIBLE);
//        }
    }
    public void onClick() {
        StoreDingDan storeDingDan = new StoreDingDan();
        storeDingDan.setUser(user.getUserId());
        storeDingDan.setStore_id(store.getId()+"");
        storeDingDan.setName(store.getName());
        storeDingDan.setMoney(store.getMoney());
        storeDingDan.setprice(store.getprice());
        storeDingDan.setType(store.getType());
        storeDingDan.setpicture(store.getpicture());
        storeDingDan.setBianhao(store.getBianhao());
        storeDingDan.setTime(longToDate());
        DBManger.getInstance(CollectionActivity.this).saveStoreDingDan(storeDingDan);
        Toast.makeText(CollectionActivity.this, "抢购成功!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public static String longToDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }

    public void initView(){

        goodname = findViewById(R.id.goodname);
        guige = findViewById(R.id.guige);
        dianPrice = findViewById(R.id.dian_price);
        tuanPrice = findViewById(R.id.tuan_price);
        goodPicture = findViewById(R.id.good_picture);
        detail = findViewById(R.id.detail);
        ccc = findViewById(R.id.ccc);
        tvSelected = findViewById(R.id.tv_selected);
        bbb = findViewById(R.id.bbb);
        tvTotal = findViewById(R.id.tv_total);
        ivCollect1 = findViewById(R.id.iv_collect1);
        ivCollect2 = findViewById(R.id.iv_collect2);
        btnTakeOrder = findViewById(R.id.btn_take_order);
        bottomLayout = findViewById(R.id.bottom_layout);
    }
}
