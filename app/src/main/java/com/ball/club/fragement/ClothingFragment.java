package com.ball.club.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ball.club.CollectionActivity;
import com.ball.club.R;
import com.ball.club.bean.Store;
import com.ball.club.bean.User;
import com.ball.club.data.DBManger;
import com.bumptech.glide.Glide;



import java.text.SimpleDateFormat;
import java.util.Date;


public class ClothingFragment extends Fragment {
    //服装详情页面
    Store store;
    TextView goodname;
    TextView type;
    ImageView goodPicture;
    TextView detail;
    Button btnTakeOrder;
    Button btnTakeDingdan;
    LinearLayout bottomLayout;

    private User user;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_good_detail, container, false);
        store = (Store) getActivity().getIntent().getSerializableExtra("DATA");
        user = DBManger.getInstance(getContext()).mUser;
        initView(view);
        goodname.setText(store.getMoney());
        type.setText(store.getName() + store.getType() + store.getBianhao());
        Glide.with(getActivity()).load(store.getpicture()).into(goodPicture);
        detail.setText(store.getprice());

        btnTakeDingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CollectionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA", store);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    public static String longToDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }

    public void initView(View view){

        goodname = view.findViewById(R.id.goodname);
        type = view.findViewById(R.id.type);
        goodPicture = view.findViewById(R.id.good_picture);
        detail = view.findViewById(R.id.detail);
        btnTakeOrder = view.findViewById(R.id.btn_take_order);
        btnTakeDingdan = view.findViewById(R.id.btn_take_dingdan);
        bottomLayout = view.findViewById(R.id.bottom_layout);

    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public void initData(){

    }
}
