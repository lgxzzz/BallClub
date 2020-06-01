package com.ball.club.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ball.club.util.SharedPreferenceUtil;


public class SQLiteDbHelper extends SQLiteOpenHelper {

    //数据库名称
    public static final String DB_NAME = "BallClub.db";
    //数据库版本号
    public static int DB_VERSION = 1;
    //用户表
    public static final String TAB_USER = "UserInfo";
    //新闻信息表
    public static final String TAB_NEWS= "News";
    //商品表
    public static final String TAB_STORE = "Store";
    //商品订单表
    public static final String TAB_STORE_DINGDAN = "StoreDingdan";

    Context context;
    public SQLiteDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableUser(db);
        createTableNews(db);
        createTableStore(db);
        createTableStoreDingdan(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        SharedPreferenceUtil.setFirstTimeUse(true,context);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_NEWS);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_STORE);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_STORE_DINGDAN);

        onCreate(db);
    }

    //创建人员表
    public void createTableUser(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_USER +
                "(USER_ID varchar(20) primary key, " +
                "USER_NAME varchar(20), " +
                "USER_PASSWORD varchar(20), " +
                "USER_TEL varchar(20), " +
                "USER_MAIL varchar(20), " +
                "USER_ROLE varchar(20))");
    }

    //创建新闻表
    public void createTableNews(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_NEWS +
                "(NEWS_ID varchar(20) primary key, " +
                "NEWS_TYPE varchar(20), " +  //种类
                "NEWS_CONTEX varchar(20), " + //病害内容
                "NEWS_URL varchar(20), " +  // 文章链接
                "NEWS_PIC_ID varchar(20))");//图片id
    }

    //商品表
    public void createTableStore(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_STORE +
                "(id integer PRIMARY KEY autoincrement,"
                + "name  text, " +
                "type text," +
                "money text, " +
                "start text, " +
                "ends text," +
                "area text," +
                "indexs text)");
    }

    //商品订单表
    public void createTableStoreDingdan(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_STORE_DINGDAN +
                "(id integer PRIMARY KEY autoincrement,"
                + "user text," +
                "store_id  text, " +
                "name  text, " +
                "type text," +
                "money text, " +
                "start text, " +
                "ends text," +
                "bianhao text," +
                "time text)");//area-原bianhao
    }
}
