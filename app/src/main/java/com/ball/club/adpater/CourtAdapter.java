package com.ball.club.adpater;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ball.club.ClothingActivity;
import com.ball.club.CourtPlaceActivity;
import com.ball.club.R;
import com.ball.club.WebViewActivity;
import com.ball.club.bean.Court;
import com.ball.club.bean.News;

import java.util.ArrayList;
import java.util.List;

public class CourtAdapter extends BaseAdapter {

    Context mContext;
    List<Court> mMsgInfos = new ArrayList<>();

    public CourtAdapter(Context mContext, List<Court> mMsgInfos){
        this.mContext = mContext;
        this.mMsgInfos = mMsgInfos;
    }

    public void setData(List<Court> mMsgInfos){
        this.mMsgInfos = mMsgInfos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mMsgInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return mMsgInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Court msgInfo = mMsgInfos.get(i);
        CourtAdapter.ViewHoler holer = null;
        if (view == null){
            holer = new CourtAdapter.ViewHoler();
            view = LayoutInflater.from(mContext).inflate(R.layout.court_item,null);
            holer.mPic = (ImageView) view.findViewById(R.id.msg_pic);
            holer.mTitle = (TextView) view.findViewById(R.id.msg_title);
            holer.mContent = (TextView) view.findViewById(R.id.msg_content);
            view.setTag(holer);
        }else{
            holer = (CourtAdapter.ViewHoler) view.getTag();
        }
        holer.mPic.setBackgroundResource(R.drawable.qiuchang);
        holer.mTitle.setText(msgInfo.getName());
        holer.mContent.setText("人数："+msgInfo.getNumber());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CourtPlaceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA", msgInfo);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    class ViewHoler{
        ImageView mPic;
        TextView mTitle;
        TextView mContent;
    }
}
