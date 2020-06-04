package com.ball.club.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ball.club.R;
import com.ball.club.bean.User;
import com.ball.club.data.DBManger;

public class AboutFragment extends Fragment {

    //个人信息
    TextView mUserID;
    TextView mUserName;
    TextView mUserTel;
    TextView mUserMail;
    Button mUpdateBtn;
    Button mStoreHistoryBtn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragement_about, container, false);
        initView(view);

        return view;
    }

    public static AboutFragment getInstance() {
        return new AboutFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public void initView(View view){
        mUserID = view.findViewById(R.id.user_id);
        mUserName = view.findViewById(R.id.user_name);
        mUserTel = view.findViewById(R.id.user_tel);
        mUserMail = view.findViewById(R.id.user_mail);
        mUpdateBtn = view.findViewById(R.id.user_update_btn);
        mStoreHistoryBtn = view.findViewById(R.id.store_his_btn);
    };

    public void initData() {
        User user = DBManger.getInstance(getContext()).mUser;
        mUserID.setText(user.getUserId());
        mUserName.setText(user.getUserName());
        mUserTel.setText(user.getTelephone());
        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mStoreHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(getContext(), TaskActivity.class);
//                Bundle b = new Bundle();
//                b.putSerializable("state","历史任务");
//                intent.putExtras(b);
//                getContext().startActivity(intent);
            }
        });


    }
}
