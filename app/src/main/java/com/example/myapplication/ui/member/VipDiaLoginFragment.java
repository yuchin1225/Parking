package com.example.myapplication.ui.member;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;


public class VipDiaLoginFragment extends MemberFragment {

    EditText member_ph;
    EditText member_car;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View login = inflater.inflate(R.layout.fragment_member_viplogin,container,false);
        member_ph = login.findViewById(R.id.vip_ph);
        member_car = login.findViewById(R.id.vip_car);
        login.findViewById(R.id.vip_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return login;
    }
}