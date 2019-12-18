package com.example.beetrootapp.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.beetrootapp.R;
import com.example.beetrootapp.ViewModel.FarmVM;
import com.example.beetrootapp.model.Farm;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener{


    private GoogleMap map;
    protected GoogleApiClient googleApiClient;
    private Location lastLocation;
    private MapView mapView;
    private View view;
    double lat =0, lng=0;

    private FarmVM farmVM;
    private List<Farm> farmList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationPermission();
        buildGoogleApiClient();
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
        map.setMyLocationEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.addMarker(new MarkerOptions()
                .position(new LatLng(50.4712, 4.85259))
                .title("Ferme de l'IESN")
                .snippet("Vend des patates"));
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
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                googleApiClient);
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


    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        lat= location.getLatitude();
        lng = location.getLongitude();
    }
}

