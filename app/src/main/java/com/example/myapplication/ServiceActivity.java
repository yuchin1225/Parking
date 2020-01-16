package com.example.myapplication;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity {

    protected List<Fragment> listfragment = new ArrayList<>();
    private long exitTime;
    ViewPager viewpage;
    BottomNavigationView nav_view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initView();
    }

    public boolean onKeyDown(int KeyCode, KeyEvent event){
        //按下返回鍵
        if(KeyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis() - exitTime) > 2000){ // 判斷2次點選事件時間
                Toast.makeText(getApplicationContext(),"再按一次返回",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else{  //點選時間<2秒返回
                return super.onKeyDown(KeyCode,event);
            }
        }
        return true;
    }

    private void initView(){
        viewpage = findViewById(R.id.viewpage);
        nav_view = findViewById(R.id.nav_view);
        initData();
    }

    private  void initData(){
        //物件滑動顯示清單
        SectionsPagerAdapter sections = new SectionsPagerAdapter(getSupportFragmentManager(),listfragment);
        //顯示清單加入viewpage物件
        viewpage.setAdapter(sections);
        //當view滑動選擇時輸出結果
        viewpage.setOnPageChangeListener(viewOnPage);
        //當nav_view物件點選時輸出結果
        nav_view.setOnNavigationItemSelectedListener(navselect);

    }


    //BottomNavigationView 物件選擇
    private BottomNavigationView.OnNavigationItemSelectedListener navselect = new BottomNavigationView.OnNavigationItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
             switch (menuItem.getItemId()){
                 case R.id.navigation_home:
                     viewpage.setCurrentItem(0);
                     return true;
                 case R.id.navigation_dashboard:
                     viewpage.setCurrentItem(1);
                     return true;
                 case R.id.navigation_coupon:
                     viewpage.setCurrentItem(2);
                     return true;
                 case R.id.navigation_vip:
                     viewpage.setCurrentItem(3);
                     return true;
                 case R.id.navigation_map:
                     viewpage.setCurrentItem(4);
                     return true;
                 default:
                     return false;
             }
         }
     };

    //Viewpager 滑動改變時
     private ViewPager.OnPageChangeListener viewOnPage = new ViewPager.OnPageChangeListener() {
         @Override
         public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

         }

         //滑動頁面取得 Fragment 內容
         @Override
         public void onPageSelected(int position) {
                nav_view.getMenu().getItem(position).setChecked(true);
         }

         @Override
         public void onPageScrollStateChanged(int state) {

         }
     };
}
