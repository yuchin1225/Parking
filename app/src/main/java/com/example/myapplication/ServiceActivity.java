package com.example.myapplication;

import android.os.Bundle;


import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;



public class ServiceActivity extends FragmentActivity {

    ViewPager viewpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_service);
        initView();
    }

    private void initView(){
        viewpage = findViewById(R.id.viewpage);
        initData();
    }

    private void initData(){
        FragmentManager fragMgr = getSupportFragmentManager();
        ServiceFragment serviceFrag = new ServiceFragment();
        fragMgr.beginTransaction()
                .add(R.id.container,serviceFrag)
                .commit();
    }
}
