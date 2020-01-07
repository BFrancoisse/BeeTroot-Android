package com.example.beetrootapp.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;


public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener{

    private GoogleMap map;
    protected GoogleApiClient googleApiClient;
    private MapView mapView;
    private List<Farm> farmsMap;

    private FarmVM farmVM;

    //SearchToolBar
    private EditText searchtext;
    private ImageView searchMagnify;

    //private ImageView mGPS;

    private FusedLocationProviderClient fusedLocationProviderClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map,container,false);
        bindById(view);
        initSearchBar();
        initClickListenerSearchMagnify();
        return view;
    }
    public void bindById(View view){
        searchtext = (EditText)view.findViewById(R.id.input_search);
        searchMagnify = (ImageView)view.findViewById(R.id.ic_magnify);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationPermission();
        buildGoogleApiClient();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

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
    }
    private void initSearchBar(){
        searchtext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        || event.getAction() == KeyEvent.KEYCODE_ENTER)
                {
                    geoLocateFarm();
                }
                return false;
            }
        });
    }
    public void initClickListenerSearchMagnify(){
        searchMagnify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geoLocateFarm();
            }
        });
    }

    private void geoLocateFarm(){
        String searchString = searchtext.getText().toString();

        for(Farm farm : farmsMap){
            if((farm.getName()).toLowerCase().equals(searchString.toLowerCase()))
            {
                String geographicCoordinates = farm.getGeographicCoordinates();
                String[] splitGC = geographicCoordinates.split(",");
                String lat = splitGC[0];
                String lng = splitGC[1];
                LatLng currentLatLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(currentLatLng,
                        11);
                map.animateCamera(update);
                return ;
            }
        }
    }
    public void insertFarmsOnMap(){
        farmsMap = new ArrayList();
        farmVM = ViewModelProviders.of(this).get(FarmVM.class);
        farmVM.getFarms(getContext()).observe(this,farms -> {
            for(Farm farm:farms)
            {
                String geographicCoordinates = farm.getGeographicCoordinates();
                String[] splitGC = geographicCoordinates.split(",");
                String lat = splitGC[0];
                String lng = splitGC[1];
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng)))
                        .title(farm.getName())
                        .icon(bitmapDescriptorFromVector(getContext(),R.drawable.ic_store_black_35dp))
                        .snippet(farm.getDescription()));
                farmsMap.add(farm);
            }
        });
    }
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
    }
    @Override
    public void onConnectionSuspended(int i) {
        // TODO
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
        // TODO
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
