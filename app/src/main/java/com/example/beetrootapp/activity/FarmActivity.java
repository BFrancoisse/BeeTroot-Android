package com.example.beetrootapp.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.beetrootapp.R;
import com.example.beetrootapp.ViewModel.FarmVM;
//import com.example.beetrootapp.ViewModel.PictureVM;
import com.example.beetrootapp.ViewModel.PictureVM;
import com.example.beetrootapp.ViewModel.UserVM;
//import com.example.beetrootapp.adapter.RecyclerViewPicturesAdapter;
import com.example.beetrootapp.model.Farm;
import com.example.beetrootapp.model.Picture;
import com.example.beetrootapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class FarmActivity extends AppCompatActivity {
    private Button buttonEdit;
    private Button buttonDirection;
    private Button buttonReview;
    private Button buttonFavourite;
    private ImageView farmPictures;
    private TextView txtFarmerName;
    private TextView txtFarmerPhone;
    private TextView txtAddress;
    private TextView txtDescription;
    //private RecyclerView mRecyclerView;
    //private RecyclerViewPicturesAdapter mAdapter;

    private String userEmail;
    private Integer farmerId;
    private int farmId;

    private UserVM userVM;
    private User user;
    private FarmVM farmVM;
    private Farm currentFarm;
    private PictureVM pictureVM;
    private List<Picture>  picturesList;

    private Context context = FarmActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        farmerId = ((Integer) getIntent().getSerializableExtra("farmerId") != null) ? (Integer) getIntent().getSerializableExtra("farmerId") : 0;

        bindViewId();
        setViewValues();
        setButtonDirection();
        setButtonReview();
        setButtonEdit();
        setButtonFavourite();
        //initRecyclerView();

        setViewValues();
        bindViewId();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem back) {

        int id = back.getItemId();
        if(id == 16908332)
            this.finish();

        return super.onOptionsItemSelected(back);
    }
    private void setViewValues() {
        this.userEmail = getIntent().getStringExtra("userEmail");


        farmVM = ViewModelProviders.of(this).get(FarmVM.class);
        if(farmerId != 0) {
            userVM = ViewModelProviders.of(this).get(UserVM.class);
            userVM.getUserById(getApplicationContext(),farmerId).observe(this, user ->{
                txtFarmerName.setText(user.getFirstname() + " " + user.getLastname());
                txtFarmerPhone.setText(user.getPhone());
            });
            farmVM.getFarmByUserId(farmerId, getApplicationContext()).observe(this, farm -> {
                txtAddress.setText(farm.getAddress().getNumber() + " " + farm.getAddress().getStreet() + ", " + farm.getAddress().getZipCode() + " " + farm.getAddress().getCity());
                txtDescription.setText(farm.getDescription());
                setTitle(farm.getName());
                this.farmId = farm.getId();
                this.currentFarm = farm;
            });
        }
        else {
            userVM = ViewModelProviders.of(this).get(UserVM.class);
            userVM.getUserByEmail(getApplicationContext(),this.userEmail).observe(this, user ->{
                /*if(user == null){
                    Toast.makeText(getApplicationContext(), R.string.noData, Toast.LENGTH_LONG).show();
                    /*Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.putExtra("userEmail", userEmail);
                    startActivity(intent);*//*
                    finish();
                    return;
                }*/
                txtFarmerName.setText(user.getFirstname() + " " + user.getLastname());
                txtFarmerPhone.setText(user.getPhone());
            });
            farmVM.getFarmByEmail(userEmail, getApplicationContext()).observe(this, farm -> {
                if(farm == null){
                    Toast.makeText(getApplicationContext(), R.string.noFarm, Toast.LENGTH_LONG).show();
                    /*Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.putExtra("userEmail", userEmail);
                    startActivity(intent);*/
                    finish();
                    return;
                }
                txtAddress.setText(farm.getAddress().getNumber() + " " + farm.getAddress().getStreet() + ", " + farm.getAddress().getZipCode() + " " + farm.getAddress().getCity());
                txtDescription.setText(farm.getDescription());
                setTitle(farm.getName());
                this.farmId = farm.getId();
                this.currentFarm = farm;
            });
        }
        //loadImageByInternetUrl();
    }
    private void bindViewId(){
        txtFarmerName = (TextView) findViewById(R.id.txtFarmerName);
        txtFarmerPhone = (TextView) findViewById(R.id.txtFarmerPhone);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        farmPictures = (ImageView) findViewById(R.id.farmPictures);
        //mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_pictures)
        // TODO : afficher images et produits proposés
    }
    /*private void initRecyclerView() {
        mAdapter = new RecyclerViewPicturesAdapter(this, pictureVM.getPicturesByFarmId(this, currentFarm.getId()).getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }*/
    private void setButtonEdit(){
        buttonEdit = (Button) findViewById(R.id.edit);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditFarmActivity.class);
                intent.putExtra("currentFarm", currentFarm);
                startActivity(intent);
                finish();
            }
        });
    }
    private void setButtonDirection(){
        buttonDirection = (Button) findViewById(R.id.direction);

        buttonDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(R.string.uriGoogleMap + currentFarm.getGeographicCoordinates()));
                startActivity(intent);
            }
        });
    }
    private void setButtonReview(){
        buttonReview = (Button) findViewById(R.id.review);

        buttonReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : Implémenter l'activity de rédaction de commentaire
                // Intent intent = new Intent(getBaseContext(), WriteReviewActivity.class);
                //startActivity(intent);
            }
        });
    }
    private void setButtonFavourite(){
        buttonFavourite = (Button) findViewById(R.id.favourite);

        buttonFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : Ajouter dans la DB un catalogue de fermes préférées
            }
        });
    }
    private void loadImageByInternetUrl() {
        picturesList = new ArrayList<>();
        pictureVM = ViewModelProviders.of(this).get(PictureVM.class);
        pictureVM
                .getPicturesByFarmId(getApplicationContext(), farmId)
                .observe(this, pictures -> {
                    System.out.println("replop");
                    for(Picture picture:pictures) {
                        String internetUrl = picture.getPictureURL();
                        System.out.println("rereplop");
                        Glide
                                .with(context)
                                .load(internetUrl)
                                .into(farmPictures);
                    }
                });
    }
}
