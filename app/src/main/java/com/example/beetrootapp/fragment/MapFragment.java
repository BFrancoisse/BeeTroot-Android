package com.example.beetrootapp.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.beetrootapp.R;
import com.example.beetrootapp.ViewModel.FarmVM;
import com.example.beetrootapp.adapter.CustomInfoWindowAdapter;
import com.example.beetrootapp.model.Farm;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.*;
import java.io.IOException;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener{


    private GoogleMap map;
    protected GoogleApiClient googleApiClient;
    private Location lastLocation;
    private MapView mapView;
    private View view;
    double lat =0, lng=0;

    private FarmVM farmVM;

    private SearchView searchView;

    private FusedLocationProviderClient fusedLocationProviderClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //setSearchView();
        return inflater.inflate(R.layout.fragment_map,container,false);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationPermission();
        buildGoogleApiClient();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        //setSearchView();
    }
    private synchronized void buildGoogleApiClient() {
        this.googleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks( this)
                .addOnConnectionFailedListener( this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize((getContext()));
        map = googleMap;
        getDeviceLocation();
        map.setMyLocationEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setInfoWindowAdapter(new CustomInfoWindowAdapter(getContext()));

        insertFarmsOnMap();
        //map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lng),5));
    }

    public void insertFarmsOnMap(){
        farmVM = ViewModelProviders.of(this).get(FarmVM.class);
        farmVM.getFarms().observe(this,farms -> {
            for(Farm farm:farms)
            {
                String geographicCoordinates = farm.getGeographicCoordinates();
                String[] splitGC = geographicCoordinates.split(",");
                String lat = splitGC[0];
                String lng = splitGC[1];
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng)))
                        .title(farm.getName())
                        .icon(bitmapDescriptorFromVector(getContext(),R.drawable.ic_store_mall_directory_black_24dp))
                        .snippet(farm.getDescription()));
            }

        });
    }

    //Custom Marker
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId){
        Drawable vectorDrawable= ContextCompat.getDrawable(context,vectorResId);
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicWidth());
        Bitmap bitmap=Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }







    void locationPermission() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }
    public void onConnected(Bundle bundle) {
        //lastLocation = LocationServices.FusedLocationApi.getLastLocation(
          //      googleApiClient);
       /* if (lastLocation != null) {
            lat = lastLocation.getLatitude();
            lng = lastLocation.getLongitude();

            loc = new LatLng(lat, lng);

        }*/
    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mapView = view.findViewById(R.id.map_view);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
            //setSearchView();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onStart() {

        googleApiClient.connect();
        super.onStart();

    }

    public void setSearchView(){
        searchView = searchView.findViewById(R.id.sv_location);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if(location != null  || !location.equals("")){
                    Geocoder geocoder = new Geocoder(getContext());
                    try{
                        addressList = geocoder.getFromLocationName(location, 1 );
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    @Override
    public void onLocationChanged(Location location) {
       /* lastLocation = location;
        lat= location.getLatitude();
        lng = location.getLongitude();*/
    }


    private void getDeviceLocation(){
        try {

                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            Location location = task.getResult();
                            LatLng currentLatLng = new LatLng(location.getLatitude(),
                                    location.getLongitude());
                            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(currentLatLng,
                                    11);
                            map.animateCamera(update);
                        }
                    }
                });

        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }
}

