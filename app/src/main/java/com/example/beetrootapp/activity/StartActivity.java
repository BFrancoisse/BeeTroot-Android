package com.example.beetrootapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beetrootapp.R;

public class StartActivity extends AppCompatActivity {
    private Button connectionButton;
    private Button inscriptionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        bindById();
        setButtonListeners();
    }


    public void bindById(){
        connectionButton = (Button)findViewById(R.id.button_connection);
        inscriptionButton = (Button)findViewById(R.id.button_inscription);
    }
    public void setButtonListeners(){
        connectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ConnectionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        inscriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), InscriptionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
