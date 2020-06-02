package com.ball.club.fragement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.ball.club.R;
import com.ball.club.data.DBManger;

import java.util.ArrayList;
import java.util.List;

public class NearFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragement_treelesion, container, false);
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

    };

    public void initData() {

    }

    //根据查询条件查询
    public void searchData(){

    }
}
