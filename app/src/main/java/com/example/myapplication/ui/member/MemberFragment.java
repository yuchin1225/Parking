package com.example.myapplication.ui.member;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.R;
import com.example.myapplication.ui.member.reg.RegFragment;


public class MemberFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View login = inflater.inflate(R.layout.fragment_member,container,false);
        login.findViewById(R.id.reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegFragment regdialog = new RegFragment();
                regdialog.show(getFragmentManager(), "regDialog");

            }
        });
        login.findViewById(R.id.vip_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VipDiaLoginFragment vipdialog = new VipDiaLoginFragment();
                vipdialog.show(getFragmentManager(), "loginDialog");
            }
        });
        return login;
    }
}