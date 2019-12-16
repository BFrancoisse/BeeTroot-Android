package com.example.beetrootapp.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beetrootapp.R;

public class EditProfileActivity extends AppCompatActivity {

    private Button saveProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setButtonSaveProfile();
    }

    public void setButtonSaveProfile(){
        saveProfile = (Button) findViewById(R.id.saveProfile);
        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Profil sauvegardé",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), UserInfoActivity.class);
                startActivity(intent);
            }
        });
    }
    public void bindEditTextId(){

    }
}
