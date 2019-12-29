package com.example.beetrootapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.beetrootapp.R;

public class HarvestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harvest);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(R.string.harvest);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem back) {

        int id = back.getItemId();
        if(id == 16908332)
            this.finish();

        return super.onOptionsItemSelected(back);
    }
    // TODO : implémenter le systeme de récoltes
}
