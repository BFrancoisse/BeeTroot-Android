package com.example.beetrootapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beetrootapp.repository.UserRepository;
import com.example.beetrootapp.R;

public class FarmActivity extends AppCompatActivity {
    private Button buttonEdit;
    private Button buttonDirection;
    private Button buttonReview;
    private Button buttonFavourite;
    private TextView txtFarmerName;
    private TextView txtFarmerPhone;
    private TextView txtAddress;
    private TextView txtDescription;

    private UserRepository userDAO;
    // private FarmDAO farmDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);

        /*userDAO = new UserDAO();
        User user = userDAO.getUserById();

        farmDAO = new FarmDAO();
        Farm farm = farmDao.getFarmById(user.getId());
*/
        setButtonEdit();
        setButtonDirection();
        setButtonReview();
        setButtonFavourite();
        bindTextViewId();

    }
    public void bindTextViewId(){
        txtFarmerName = (TextView) findViewById(R.id.txtFarmerName);
        txtFarmerPhone = (TextView) findViewById(R.id.txtFarmerName);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtDescription = (TextView) findViewById(R.id.txtDescription);


    }
    public void setButtonEdit(){
        buttonEdit = (Button) findViewById(R.id.edit);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditFarmActivity.class);
                startActivity(intent);
            }
        });
    }
    public void setButtonDirection(){
        buttonDirection = (Button) findViewById(R.id.direction);

        buttonDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ouvrir GMaps avec les bonnes coordonnées
            }
        });
    }
    public void setButtonReview(){
        buttonReview = (Button) findViewById(R.id.review);

        buttonReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implémenter l'activity de rédaction de commentaire
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
                // Ajouter dans la DB un catalogue de fermes préférées

            }
        });
    }
}
