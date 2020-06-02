package com.ball.club.adpater;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ball.club.R;
import com.ball.club.WebViewActivity;
import com.ball.club.bean.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends BaseAdapter {

    Context mContext;
    List<News> mMsgInfos = new ArrayList<>();

    public NewsAdapter(Context mContext, List<News> mMsgInfos){
        this.mContext = mContext;
        this.mMsgInfos = mMsgInfos;
    }

    public void setData(List<News> mMsgInfos){
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
        final News msgInfo = mMsgInfos.get(i);
        NewsAdapter.ViewHoler holer = null;
        if (view == null){
            holer = new NewsAdapter.ViewHoler();
            view = LayoutInflater.from(mContext).inflate(R.layout.msg_item,null);
            holer.mPic = (ImageView) view.findViewById(R.id.msg_pic);
            holer.mTitle = (TextView) view.findViewById(R.id.msg_title);
            holer.mContent = (TextView) view.findViewById(R.id.msg_content);
            view.setTag(holer);
        }else{
            holer = (NewsAdapter.ViewHoler) view.getTag();
        }
        holer.mPic.setBackgroundResource(msgInfo.getNEWS_PIC_ID());
        holer.mTitle.setText(msgInfo.getNEWS_TYPE());
        holer.mContent.setText(msgInfo.getNEWS_CONTEX());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext,WebViewActivity.class);
                intent.putExtra("url",msgInfo.getNEWS_URL());
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
