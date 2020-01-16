package com.example.myapplication.ui.member.reg;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
        reg_ph.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        reg_car = view.findViewById(R.id.reg_car);
        reg_name = view.findViewById(R.id.reg_name);
        reg_next_page = view.findViewById(R.id.reg_next_page);
        reg_next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                RegPageFragment regpage = new RegPageFragment();
                String ph = reg_ph.getText().toString().trim();
                String car = reg_car.getText().toString().trim();
                String name = reg_name.getText().toString().trim();
                bundle.putString("ph",ph);
                bundle.putString("car",car);
                bundle.putString("name",name);
                regpage.setArguments(bundle);
                regpage.setCancelable(false);
                if(reg_ph.getText().toString().trim().length() >=10 && reg_ph.getText().toString().trim() !=null){
                    Toast.makeText(getContext(),"已傳送簡訊至" + ph,Toast.LENGTH_LONG).show();
                    regpage.show(getFragmentManager(),"Regpage");
                    dismiss();
                }else{
                    Toast.makeText(getContext(),"請輸入正確手機號碼",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}