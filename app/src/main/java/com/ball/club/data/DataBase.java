package com.ball.club.data;

import com.ball.club.R;
import com.ball.club.bean.News;
import com.ball.club.bean.Store;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    //新闻标题
    String[] NewsType = new String[]{

    };

    //新闻内容
    String[] NewsContext = new String[]{

    };

    //新闻链接
    public String[] NEWS_URL = new String[]{

    };

    //新闻图片
    public int[] NEWS_PIC = new int[]{

    };


    //商品信息
    public String[] STORES =new String[]{
      ""
    };

    public List<Store> mStoreList = new ArrayList<>();
    public List<News> mNewsList = new ArrayList<>();

    public DataBase(){
        for (int i=0;i<STORES.length;i++){
            Store store = new Store();

            mStoreList.add(store);
        }

        for (int i=0;i<NewsType.length;i++){
            News news = new News();
            news.setNEWS_ID(getRandomNews_ID());
            news.setNEWS_TYPE(NewsType[i]);
            news.setNEWS_CONTEX(NewsContext[i]);
            news.setNEWS_PIC_ID(NEWS_PIC[i]);
            news.setNEWS_URL(NEWS_URL[i]);
            mNewsList.add(news);
        }
    }

    //生成商品id
    public static String getRandomStore_ID(){
        String strRand="T" ;
        for(int i=0;i<10;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }

    //生成新闻id
    public static String getRandomNews_ID(){
        String strRand="N" ;
        for(int i=0;i<10;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }
}
