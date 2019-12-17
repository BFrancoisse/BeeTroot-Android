package com.example.beetrootapp.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beetrootapp.R;

public class EditFarmActivity extends AppCompatActivity {

    private Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_farm);

        setButtonSave();
    }

    public void setButtonSave(){
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Modifications sauvegardées",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), FarmActivity.class);
                startActivity(intent);
            }
        });
    }
    public void bindEditTextId(){

    }
}
