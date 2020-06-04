package com.ball.club.data;

import com.ball.club.R;
import com.ball.club.bean.News;
import com.ball.club.bean.Store;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    //新闻标题
    String[] NewsType = new String[]{
            "赛季大猜想：进步最快奖候选之英格拉姆",
            "泰肖恩-普林斯有可能成为活塞的总经理助理",
            "圆桌会议周中版：雷霆队的优缺点以及前景"
    };

    //新闻内容
    String[] NewsContext = new String[]{
            "或许直到很多年后我们依然会继续谈论湖人与鹈鹕的那笔大交易。对于鹈鹕来说，正应了那句话：“你可能不亏，我永远血赚。”——因为英格拉姆今年打得太好了。",
            "《底特律自由报》的记者奥马里-桑科法对此说道：“活塞目前正在招聘两名高管——一个担任球队总经理，一个担任球队总经理助理。普林斯是后一职位的有力角逐者。”",
            "雷霆在休赛期历经阵容巨变后受到外界普遍看衰，但他们本赛季却在克里斯-保罗的带领下逆势而起成为黑马，当前排名高居西部第五。那么这支球队现在有何优缺点，待到赛季恢复他们又能在季后赛走多远呢？一起来看官网编辑对此的讨论："
    };

    //新闻链接
    public String[] NEWS_URL = new String[]{
            "https://nbachina.qq.com/a/20200603/002607.htm",
            "https://nbachina.qq.com/a/20200604/004473.htm",
            "https://nbachina.qq.com/a/20200603/004130.htm"
    };

    //新闻图片
    public int[] NEWS_PIC = new int[]{
            R.drawable.news_1,
            R.drawable.news_2,
            R.drawable.news_3,
    };

    //商品信息
    public String[] STORES =new String[]{
            "运动,安踏,400,400,0,0",
            "运动,阿迪,1500,1500,0,0",
            "休闲,NIKE,500,700,0,0",
    };

    //新闻图片
    public int[] STORE_PIC = new int[]{
            R.drawable.store_1,
            R.drawable.store_2,
            R.drawable.store_3,
    };

    public List<Store> mStoreList = new ArrayList<>();
    public List<News> mNewsList = new ArrayList<>();

    private String name,type,money,price,bianhao,index;
    private int picture;

    public DataBase(){
        for (int i=0;i<STORES.length;i++){
            String[] params = STORES[i].split(",");
            Store store = new Store();
            store.setName(params[0]);
            store.setType(params[1]);
            store.setMoney(params[2]);
            store.setPrice(params[3]);
            store.setIndex(params[4]);
            store.setBianhao(params[5]);
            store.setpicture(STORE_PIC[i]);
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
