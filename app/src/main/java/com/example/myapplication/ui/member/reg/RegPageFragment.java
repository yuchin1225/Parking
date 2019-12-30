package com.example.myapplication.ui.member.reg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.ui.member.MemberFragment;


public class RegPageFragment extends MemberFragment {

    EditText reg_ph_chick,chick_phcode;
    Button reg_next_page_1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reg_page1,container,false);
        reg_ph_chick = view.findViewById(R.id.reg_ph_chick);
        reg_ph_chick.setText("Get you Phone");
        reg_ph_chick.setEnabled(false);
        chick_phcode = view.findViewById(R.id.chick_phcode);
        reg_next_page_1 = view.findViewById(R.id.reg_next_page_1);
        reg_next_page_1.setOnClickListener(nextpageOnClick);
        return view;
    }
    public View.OnClickListener nextpageOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(),"Coming soon...",Toast.LENGTH_LONG).show();
            dismiss();
        }
    };
}