package com.example.beetrootapp.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.beetrootapp.R;
import com.example.beetrootapp.model.Farm;
import com.example.beetrootapp.repository.FarmRepository;

public class EditFarmActivity extends AppCompatActivity {

    private Button save;
    private EditText editName;
    private EditText editDecription;
    private EditText editStreet;
    private EditText editNumberHouse;
    private EditText editZipCode;
    private EditText editLocality;

    private AwesomeValidation awesomeValidation;

    private Farm currentFarm;
    private FarmRepository farmRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_farm);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currentFarm = (Farm) getIntent().getSerializableExtra("currentFarm");

        bindEditTextId();
        setEditTextValues();

        setButtonSave();
        setTitle("Editer ma ferme");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem back) {
        int id = back.getItemId();
        if(id == 16908332) {
            Intent intent = new Intent(this, FarmActivity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onOptionsItemSelected(back);
    }
    public void setEditTextValues(){
        editName.setText(currentFarm.getName());
        editDecription.setText(currentFarm.getDescription());
        editStreet.setText(currentFarm.getAddress().getStreet());
        editNumberHouse.setText(currentFarm.getAddress().getNumber());
        editZipCode.setText(String.valueOf(currentFarm.getAddress().getZipCode()));
        editLocality.setText(currentFarm.getAddress().getCity());
    }
    public void setButtonSave(){
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    Toast.makeText(getApplicationContext(), R.string.farmSaved, Toast.LENGTH_LONG).show();
                    currentFarm.setName(editName.getText().toString());
                    currentFarm.setDescription(editDecription.getText().toString());
                    currentFarm.getAddress().setStreet(editStreet.getText().toString());
                    currentFarm.getAddress().setNumber(editNumberHouse.getText().toString());
                    currentFarm.getAddress().setZipCode(Integer.parseInt(editZipCode.getText().toString()));
                    currentFarm.getAddress().setCity(editLocality.getText().toString());

                    farmRepository = new FarmRepository();
                    //farmRepository.updateFarm(farm);

                    Intent intent = new Intent(getBaseContext(), FarmActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    public void bindEditTextId(){
        editName = (EditText) findViewById(R.id.editName);
        editDecription = (EditText) findViewById(R.id.editDescription);
        editStreet = (EditText) findViewById(R.id.editStreet);
        editNumberHouse = (EditText) findViewById(R.id.editNumberHouse);
        editZipCode = (EditText) findViewById(R.id.editZipCode);
        editLocality = (EditText) findViewById(R.id.editLocality);

        // Add validation
        

    }
}
