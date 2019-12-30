package com.example.myapplication.ui.member.reg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.ui.member.MemberFragment;


public class RegFragment extends MemberFragment {

    EditText reg_ph,reg_car,reg_name;
    Button reg_next_page;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_reg,container,false);
        reg_ph = view.findViewById(R.id.reg_ph);
        reg_car = view.findViewById(R.id.reg_car);
        reg_name = view.findViewById(R.id.reg_name);
        reg_next_page = view.findViewById(R.id.reg_next_page);
        reg_next_page.setOnClickListener(nextpageOnClick);
        return view;
    }
    public View.OnClickListener nextpageOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RegPageFragment regpage = new RegPageFragment();
            regpage.show(getFragmentManager(),"Regpage");
            regpage.setCancelable(false);
            dismiss();
        }
    };
}