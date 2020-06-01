package com.ball.club;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.ball.club.bean.User;
import com.ball.club.data.DBManger;
import com.ball.club.fragement.AboutFragment;
import com.ball.club.fragement.BuyFragment;
import com.ball.club.fragement.NearFragment;
import com.ball.club.fragement.NewsFragment;
import com.ball.club.util.FragmentUtils;


public class MainActivity extends BaseActivtiy {

    private BottomNavigationView mBottomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window win = getWindow ();
        WindowManager.LayoutParams params = win.getAttributes ();
        win.setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        requestPermissions();
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        init();

    }

    public void init(){
        User mUser = DBManger.getInstance(this).mUser;
        mBottomMenu = findViewById(R.id.bottom_menu);

        mBottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                showFragment(item.getItemId());
                return true;
            }
        });
        showFragment(R.id.bottom_menu_news);
    }


    /**
     * 根据id显示相应的页面
     * @param menu_id
     */
    private void showFragment(int menu_id) {
        switch (menu_id){
            case R.id.bottom_menu_news:
                FragmentUtils.replaceFragmentToActivity(fragmentManager, NewsFragment.getInstance(),R.id.main_frame);
                break;
            case R.id.bottom_menu_buy:
                FragmentUtils.replaceFragmentToActivity(fragmentManager, BuyFragment.getInstance(),R.id.main_frame);
                break;
            case R.id.bottom_menu_near:
                FragmentUtils.replaceFragmentToActivity(fragmentManager, NearFragment.getInstance(),R.id.main_frame);
                break;
            case R.id.bottom_menu_about:
                FragmentUtils.replaceFragmentToActivity(fragmentManager, AboutFragment.getInstance(),R.id.main_frame);
                break;
        }
    }

    private void requestPermissions(){
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                int permission = ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA);
                if(permission!= PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String[]
                            {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.WRITE_SETTINGS,Manifest.permission.READ_EXTERNAL_STORAGE,
                                    },0x0010);
                }

                if(permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String[] {
                            Manifest.permission.WRITE_SETTINGS,Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},0x0010);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
