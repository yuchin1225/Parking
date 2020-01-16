package com.example.myapplication.ui.map;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map,container,false);
        initDate();
        return view;
    }

    private void initDate(){
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapfrag);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    googleMap.clear();
                    CameraPosition GooglePlex = CameraPosition.builder().target(new LatLng(22.6034985,120.276629))
                            .zoom(10).bearing(0).tilt(90).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(GooglePlex) ,10,null);
                    googleMap.setMyLocationEnabled(true);
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(22.612385,120.3081431))
                            .title("新詠科技-高雄")
                            .snippet("His Talent : Plenty of money"));
            }
        });
    }
}
