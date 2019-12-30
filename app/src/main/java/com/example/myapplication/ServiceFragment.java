package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.ui.coupon.CouponFragment;
import com.example.myapplication.ui.dashboard.DashboardFragment;
import com.example.myapplication.ui.home.HomeFragment;
import com.example.myapplication.ui.map.MapFragment;
import com.example.myapplication.ui.member.MemberFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ServiceFragment extends Fragment {
    List<Fragment> listfragment = new ArrayList<>();
    ViewPager viewpage;
    BottomNavigationView nav_view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View main = inflater.inflate(R.layout.fragment_service,container,false);
        return main;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        viewpage = getActivity().findViewById(R.id.viewpage);
        nav_view = getActivity().findViewById(R.id.nav_view);
        initData();
    }

    private  void initData(){
        listfragment.add( 0,new HomeFragment());
        listfragment.add(1,new DashboardFragment());
        listfragment.add(2,new CouponFragment());
        listfragment.add(3,new MemberFragment());
        listfragment.add(4,new MapFragment());
        SectionsPagerAdapter sections = new SectionsPagerAdapter(getFragmentManager(),listfragment);
        viewpage.setAdapter(sections);
        nav_view.setOnNavigationItemSelectedListener(navselect);
        viewpage.setOnPageChangeListener(viewOnPage);
    }

    public BottomNavigationView.OnNavigationItemSelectedListener navselect = new BottomNavigationView.OnNavigationItemSelectedListener() {
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

     public ViewPager.OnPageChangeListener viewOnPage = new ViewPager.OnPageChangeListener() {
         @Override
         public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

         }

         @Override
         public void onPageSelected(int position) {
                nav_view.getMenu().getItem(position).setChecked(true);
         }

         @Override
         public void onPageScrollStateChanged(int state) {

         }
     };
}
