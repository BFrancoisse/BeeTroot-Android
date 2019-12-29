package com.example.beetrootapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.beetrootapp.R;
import com.example.beetrootapp.ViewModel.FarmVM;
import com.example.beetrootapp.ViewModel.UserVM;
import com.example.beetrootapp.model.Farm;
import com.example.beetrootapp.model.User;

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

    private UserVM userVM;
    private User user;
    private FarmVM farmVM;
    private Farm farm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setButtonEdit();
        setButtonDirection();
        setButtonReview();
        setButtonFavourite();
        setTextViewValues();
        bindViewId();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem back) {

        int id = back.getItemId();
        if(id == 16908332)
            this.finish();

        return super.onOptionsItemSelected(back);
    }
    public void setTextViewValues() {
        userVM = ViewModelProviders.of(this).get(UserVM.class);
        userVM.getUserById(getApplicationContext()).observe(this, user -> this.user = user);
        farmVM = ViewModelProviders.of(this).get(FarmVM.class);
        farmVM.getFarmByUserId(3,getApplicationContext()).observe(this, farm ->{
            txtFarmerName.setText(this.user.getFirstname());
            txtAddress.setText(farm.getAddress().getNumber() + " " + farm.getAddress().getStreet() + ", " + farm.getAddress().getZipCode() + " " + farm.getAddress().getCity());
            txtDescription.setText(farm.getDescription());
            txtFarmerPhone.setText(this.user.getPhone());
            setTitle(farm.getName());

            this.farm = farm;
        });
    }
    public void bindViewId(){
        txtFarmerName = (TextView) findViewById(R.id.txtFarmerName);
        txtFarmerPhone = (TextView) findViewById(R.id.txtFarmerPhone);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        farmPictures = (ImageView) findViewById(R.id.farmPictures);
        // TODO : afficher images et produits proposés
    }
    public void setButtonEdit(){
        buttonEdit = (Button) findViewById(R.id.edit);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditFarmActivity.class);
                intent.putExtra("currentFarm", farm);
                startActivity(intent);
                finish();
            }
        });
    }
    public void setButtonDirection(){
        buttonDirection = (Button) findViewById(R.id.direction);

        buttonDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" + farm.getGeographicCoordinates()));
                startActivity(intent);
            }
        });
    }
    public void setButtonReview(){
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
    public void setButtonFavourite(){
        buttonFavourite = (Button) findViewById(R.id.favourite);

        buttonFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : Ajouter dans la DB un catalogue de fermes préférées
            }
        });
    }
}
