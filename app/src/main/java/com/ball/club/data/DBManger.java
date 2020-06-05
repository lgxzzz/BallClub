package com.ball.club.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.ball.club.bean.News;
import com.ball.club.bean.Store;
import com.ball.club.bean.StoreDingDan;
import com.ball.club.bean.User;
import com.ball.club.util.SharedPreferenceUtil;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DBManger {
    private Context mContext;
    private SQLiteDbHelper mDBHelper;
    public User mUser;
    public DataBase mDataBase;
    public static  DBManger instance;
    public static DBManger getInstance(Context mContext){
        if (instance == null){
            instance = new DBManger(mContext);
        }
        return instance;
    };

    public DBManger(final Context mContext){
        this.mContext = mContext;
        mDBHelper = new SQLiteDbHelper(mContext);
        mDataBase = new DataBase();
        if (SharedPreferenceUtil.getFirstTimeUse(mContext)){
            initDefaultData();
            SharedPreferenceUtil.setFirstTimeUse(false,mContext);
        }
    }


    //用户登陆
    public void login(String name, String password, IListener listener){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from UserInfo where USER_NAME =? and USER_PASSWORD=?",new String[]{name,password});
            if (cursor.moveToFirst()){
                String USER_ID = cursor.getString(cursor.getColumnIndex("USER_ID"));
                String USER_NAME = cursor.getString(cursor.getColumnIndex("USER_NAME"));
                String USER_MAIL = cursor.getString(cursor.getColumnIndex("USER_MAIL"));
                String USER_TEL = cursor.getString(cursor.getColumnIndex("USER_TEL"));
                String USER_ROLE = cursor.getString(cursor.getColumnIndex("USER_ROLE"));

                mUser = new User();
                mUser.setUserId(USER_ID);
                mUser.setUserName(USER_NAME);
                mUser.setTelephone(USER_TEL);
                listener.onSuccess();
            }else{
                listener.onError("未查询到该用户");
            }
            db.close();
            return;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        listener.onError("未查询到该用户");
    }

    //修改用户信息
    public void updateUser(User user,IListener listener){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from UserInfo where USER_NAME=?",new String[]{user.getUserName()});
            if (cursor.moveToFirst()){
                ContentValues values = new ContentValues();
                values.put("USER_NAME",user.getUserName());
                values.put("USER_TEL",user.getTelephone());
                values.put("USER_PASSWORD",user.getPassword());

                int code = db.update(SQLiteDbHelper.TAB_USER,values,"USER_NAME =?",new String[]{user.getUserName()+""});
                listener.onSuccess();
            }else {
                insertUser(user,listener);
            }
            db.close();
        }catch (Exception e){

        }
    }

    //注册用户
    public void registerUser(User user,IListener listener){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from UserInfo where USER_NAME=?",new String[]{user.getUserName()});
            if (cursor.moveToFirst()){
                listener.onError("用户名已经被注册！");
            }else{
                String userid = getRandomUSER_ID();
                ContentValues values = new ContentValues();
                values.put("USER_ID",userid);
                values.put("USER_NAME",user.getUserName());
                values.put("USER_PASSWORD",user.getPassword());
                mUser = user;
                mUser.setUserId(userid);
                mUser.setUserName(user.getUserName());
                mUser.setTelephone(user.getTelephone());
                long code = db.insert(SQLiteDbHelper.TAB_USER,null,values);
                listener.onSuccess();
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    };

    //注册用户
    public void insertUser(User user,IListener listener){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from UserInfo where USER_NAME=?",new String[]{user.getUserName()});
            if (cursor.moveToFirst()){
                listener.onError("用户名已经被注册！");
            }else{
                String userid = getRandomUSER_ID();
                ContentValues values = new ContentValues();
                values.put("USER_ID",userid);
                values.put("USER_NAME",user.getUserName());
                values.put("USER_PASSWORD",user.getPassword());
                values.put("USER_TEL",user.getTelephone());
                long code = db.insert(SQLiteDbHelper.TAB_USER,null,values);
                listener.onSuccess();
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    };


    //生成随机userid
    public String getRandomUSER_ID(){
        String strRand="LF" ;
        for(int i=0;i<10;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }

    //获取所有用户
    public List<User> getAllUsers(){
        List<User> mUsers = new ArrayList<>();
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.query(SQLiteDbHelper.TAB_USER,null,null,null,null,null,null);
            while (cursor.moveToNext()){
                String USER_ID = cursor.getString(cursor.getColumnIndex("USER_ID"));
                String USER_NAME = cursor.getString(cursor.getColumnIndex("USER_NAME"));
                String USER_PASSWORD = cursor.getString(cursor.getColumnIndex("USER_PASSWORD"));
                String USER_TEL = cursor.getString(cursor.getColumnIndex("USER_TEL"));
                String USER_MAIL = cursor.getString(cursor.getColumnIndex("USER_MAIL"));
                String USER_ROLE = cursor.getString(cursor.getColumnIndex("USER_ROLE"));

                User user = new User();
                user.setUserId(USER_ID);
                user.setUserName(USER_NAME);
                user.setPassword(USER_PASSWORD);
                user.setTelephone(USER_TEL);
                mUsers.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mUsers;
    }

    //获取新闻信息
    public List<News> getAllNews(){

        List<News> mNewsList = new ArrayList<>();
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.query(SQLiteDbHelper.TAB_NEWS,null,null,null,null,null,null);
            while (cursor.moveToNext()){
                String NEWS_ID = cursor.getString(cursor.getColumnIndex("NEWS_ID"));
                String NEWS_TYPE = cursor.getString(cursor.getColumnIndex("NEWS_TYPE"));
                String NEWS_CONTEX = cursor.getString(cursor.getColumnIndex("NEWS_CONTEX"));
                String NEWS_URL = cursor.getString(cursor.getColumnIndex("NEWS_URL"));
                String NEWS_PIC_ID = cursor.getString(cursor.getColumnIndex("NEWS_PIC_ID"));

                News pest = new News();
                pest.setNEWS_ID(NEWS_ID);
                pest.setNEWS_TYPE(NEWS_TYPE);
                pest.setNEWS_CONTEX(NEWS_CONTEX);
                pest.setNEWS_PIC_ID(Integer.parseInt(NEWS_PIC_ID));
                pest.setNEWS_URL(NEWS_URL);

                mNewsList.add(pest);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mNewsList;
    }

    //根据条件查询新闻信息
    public List<News> getNewsByKey(String key){
        List<News> mNewsList = new ArrayList<>();
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM News WHERE NEWS_TYPE LIKE '%" + key + "%'", null);
            while (cursor.moveToNext()){
                String NEWS_ID = cursor.getString(cursor.getColumnIndex("NEWS_ID"));
                String NEWS_TYPE = cursor.getString(cursor.getColumnIndex("NEWS_TYPE"));
                String NEWS_CONTEX = cursor.getString(cursor.getColumnIndex("NEWS_CONTEX"));
                String NEWS_URL = cursor.getString(cursor.getColumnIndex("NEWS_URL"));
                String NEWS_PIC_ID = cursor.getString(cursor.getColumnIndex("NEWS_PIC_ID"));

                News pest = new News();
                pest.setNEWS_ID(NEWS_ID);
                pest.setNEWS_TYPE(NEWS_TYPE);
                pest.setNEWS_CONTEX(NEWS_CONTEX);
                pest.setNEWS_PIC_ID(Integer.parseInt(NEWS_PIC_ID));
                pest.setNEWS_URL(NEWS_URL);

                mNewsList.add(pest);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mNewsList;
    }

    //添加新闻
    public void insertNews(News news){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("NEWS_ID",news.getNEWS_ID());
            values.put("NEWS_TYPE",news.getNEWS_TYPE());
            values.put("NEWS_CONTEX",news.getNEWS_CONTEX());
            values.put("NEWS_URL",news.getNEWS_URL());
            values.put("NEWS_PIC_ID",news.getNEWS_PIC_ID()+"");
            long code = db.insert(SQLiteDbHelper.TAB_NEWS,null,values);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //添加店铺数据
    public void insertStore(Store bean) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL("INSERT INTO store(name, type,money, start, ends,area,indexs) " +
                "VALUES ('" + bean.getName()
                + "', '" + bean.getType()
                + "', '" + bean.getMoney()
                + "', '" + bean.getprice()
                + "', '" + bean.getpicture()
                + "', '" + bean.getBianhao()
                + "', '" + bean.getIndex()
                + "')");


    }

    //根据条件查询新闻信息
    public List<Store> getStoreByKey(String key){
        List<Store> records = new ArrayList<Store>();
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Store WHERE name LIKE '%" + key + "%'", null);
            while (cursor.moveToNext()){
                Store bean = new Store();
                bean.setId(cursor.getInt(cursor.getColumnIndex("id")));
                bean.setName(cursor.getString(cursor.getColumnIndex("name")));
                bean.setType(cursor.getString(cursor.getColumnIndex("type")));

                bean.setMoney(cursor.getString(cursor.getColumnIndex("money")));
                bean.setprice(cursor.getString(cursor.getColumnIndex("start")));
                bean.setpicture(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ends"))));
                bean.setBianhao(cursor.getString(cursor.getColumnIndex("area")));//area-原bianhao
                bean.setIndex(cursor.getString(cursor.getColumnIndex("indexs")));

                records.add(bean);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return records;
    }

    /**
     * 获取所有商品数据
     *
     * @return
     */
    public List<Store> getAllStore() {
        List<Store> records = new ArrayList<Store>();
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        if (db != null) {
            Cursor cursor = db.rawQuery("SELECT * FROM store ", null);
            while (cursor.moveToNext()) {
                Store bean = new Store();
                bean.setId(cursor.getInt(cursor.getColumnIndex("id")));
                bean.setName(cursor.getString(cursor.getColumnIndex("name")));
                bean.setType(cursor.getString(cursor.getColumnIndex("type")));

                bean.setMoney(cursor.getString(cursor.getColumnIndex("money")));
                bean.setprice(cursor.getString(cursor.getColumnIndex("start")));
                bean.setpicture(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ends"))));
                bean.setBianhao(cursor.getString(cursor.getColumnIndex("area")));//area-原bianhao
                bean.setIndex(cursor.getString(cursor.getColumnIndex("indexs")));

                records.add(bean);
            }
            cursor.close();
        }

        return records;
    }

    //存储商品订单数据
    public void saveStoreDingDan(StoreDingDan bean) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL("INSERT INTO storedingdan(user,store_id,name, type,money, start, ends,bianhao,time) " +
                "VALUES ('" + bean.getUser()
                + "', '" + bean.getStore_id()
                + "', '" + bean.getName()
                + "', '" + bean.getType()
                + "', '" + bean.getMoney()
                + "', '" + bean.getprice()
                + "', '" + bean.getpicture()
                + "', '" + bean.getBianhao()//area-原bianhao
                + "', '" + bean.getTime()
                + "')");


    }

    /**
     * 获取所有商品订单数据
     *
     * @return
     */
    public List<StoreDingDan> getAllStoreDingDan(String user) {
        List<StoreDingDan> records = new ArrayList<StoreDingDan>();
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        if (db != null) {
            Cursor cursor =null;
            if (TextUtils.isEmpty(user)){
                cursor = db.rawQuery("SELECT * FROM storedingdan", null);

            }else {
                cursor = db.rawQuery("SELECT * FROM storedingdan WHERE user = ?", new String[]{user});

            }
            while (cursor.moveToNext()) {
                StoreDingDan bean = new StoreDingDan();
                bean.setId(cursor.getInt(cursor.getColumnIndex("id")));
                bean.setUser(cursor.getString(cursor.getColumnIndex("user")));
                bean.setStore_id(cursor.getString(cursor.getColumnIndex("store_id")));

                bean.setName(cursor.getString(cursor.getColumnIndex("name")));
                bean.setType(cursor.getString(cursor.getColumnIndex("type")));

                bean.setMoney(cursor.getString(cursor.getColumnIndex("money")));
                bean.setprice(cursor.getString(cursor.getColumnIndex("start")));
                bean.setpicture(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ends"))));
                bean.setBianhao(cursor.getString(cursor.getColumnIndex("bianhao")));//area-原bianhao
                bean.setTime(cursor.getString(cursor.getColumnIndex("time")));

                records.add(bean);
            }
            cursor.close();
        }
        return records;
    }


    public void initDefaultData(){
        List<Store> mStoreList = mDataBase.mStoreList;
        for (int i =0;i<mStoreList.size();i++){
            Store store = mStoreList.get(i);
            insertStore(store);
        }

        List<News> mNewsList = mDataBase.mNewsList;
        for (int i =0;i<mNewsList.size();i++){
            News news = mNewsList.get(i);
            insertNews(news);
        }
    }

    public interface IListener{
        public void onSuccess();
        public void onError(String error);
    };


}
