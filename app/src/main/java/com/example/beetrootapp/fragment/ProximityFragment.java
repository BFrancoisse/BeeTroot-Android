package com.example.beetrootapp.fragment;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beetrootapp.R;
import com.example.beetrootapp.ViewModel.FarmVM;
import com.example.beetrootapp.activity.FarmActivity;
import com.example.beetrootapp.adapter.RecyclerViewProximityAdapter;
import com.example.beetrootapp.model.Farm;
import com.example.beetrootapp.model.User;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProximityFragment extends Fragment {

    private ArrayList<Farm> farmsProximity;
    private FarmVM farmVM;
    private RecyclerView recyclerView;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location locationUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_proximity, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_proximity);
        getDeviceLocation();

        farmsProximity = new ArrayList<>();

        farmVM = ViewModelProviders.of(this).get(FarmVM.class);
        farmVM.getFarms(getContext()).observe(this, farms -> {
            farmsProximity = (ArrayList<Farm>) farms;
            getDistancesBetweenUserFarm();
            initRecyclerView();
        });
        return view;
    }
    private void initRecyclerView(){
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewProximityAdapter recyclerViewProximityAdapter = new RecyclerViewProximityAdapter(farmsProximity,this.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerViewProximityAdapter);
    }
    private void getDistancesBetweenUserFarm(){
        for(Farm farm : farmsProximity)
        {
            String geographicCoordinates = farm.getGeographicCoordinates();
            String[] splitGC = geographicCoordinates.split(",");
            String lat = splitGC[0];
            String lng = splitGC[1];
            Location locationFarm = new Location("LocationFarm");
            locationFarm.setLatitude(Double.parseDouble(lat));
            locationFarm.setLongitude(Double.parseDouble(lng));
            farm.setDistance(locationUser.distanceTo(locationFarm) / 1000);
        }
        Collections.sort(farmsProximity, new Comparator<Farm>() {
            @Override
            public int compare(Farm f1, Farm f2) {
                return Float.compare(f1.getDistance(),f2.getDistance());
            }
        });
    }
    private void getDeviceLocation(){
        try {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
            Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful()) {
                        Location location = task.getResult();
                        locationUser = new Location("LocationUser");
                        locationUser.setLatitude(location.getLatitude());
                        locationUser.setLongitude(location.getLongitude());
                    }
                }
            });
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }
}
