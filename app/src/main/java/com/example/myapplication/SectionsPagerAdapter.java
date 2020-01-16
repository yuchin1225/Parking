package com.example.myapplication;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.myapplication.ui.coupon.CouponFragment;
import com.example.myapplication.ui.dashboard.DashboardFragment;
import com.example.myapplication.ui.home.HomeFragment;
import com.example.myapplication.ui.map.MapFragment;
import com.example.myapplication.ui.member.MemberFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> listFragment = new ArrayList<>();
    private FragmentManager fragmentManager;

    public SectionsPagerAdapter(FragmentManager fm, List<Fragment> listFragment) {
        super(fm);
        this.fragmentManager = fm;
        this.listFragment = listFragment;

        //將fragment加入listfragment
        listFragment.add( 0,new HomeFragment());
        listFragment.add(1,new DashboardFragment());
        listFragment.add(2,new CouponFragment());
        listFragment.add(3,new MemberFragment());
        listFragment.add(4,new MapFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);  //listfragment中取得fragment
    }

    @Override
    public int getCount() {
        return listFragment.size(); //取得 listfragment 數目
    }
}