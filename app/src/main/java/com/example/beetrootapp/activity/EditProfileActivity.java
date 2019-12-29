package com.example.beetrootapp.activity;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.beetrootapp.R;
import com.example.beetrootapp.ViewModel.UserVM;
import com.example.beetrootapp.model.User;
import com.example.beetrootapp.repository.UserRepository;

public class EditProfileActivity extends AppCompatActivity {

    private Button saveProfile;
    private EditText editFirstname;
    private EditText editLastname;
    private EditText editEmail;
    private EditText editPhone;
    private EditText editStreet;
    private EditText editNumberHouse;
    private EditText editZipCode;
    private EditText editLocality;

    private AwesomeValidation awesomeValidation;

    private UserVM userVM;
    private User currentUser;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         currentUser = (User) getIntent().getSerializableExtra("currentUser");

        bindEditTextId();
        setEditTextValues();

        setButtonSaveProfile();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == 16908332) {
            Intent intent = new Intent(this, UserInfoActivity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

   public void setEditTextValues(){
        if(currentUser == null)
            Toast.makeText(getApplicationContext(), R.string.noData, Toast.LENGTH_LONG).show();
         else {
            editFirstname.setText(currentUser.getFirstname());
            editLastname.setText(currentUser.getLastname());
            editEmail.setText(currentUser.getEmail());
            editPhone.setText(currentUser.getPhone());
            editStreet.setText(currentUser.getAddress().getStreet());
            editNumberHouse.setText(currentUser.getAddress().getNumber());
            editZipCode.setText(String.valueOf(currentUser.getAddress().getZipCode()));
            editLocality.setText(currentUser.getAddress().getCity());
        }
    }

    public void setButtonSaveProfile(){
        saveProfile = (Button) findViewById(R.id.saveProfile);
        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    Toast.makeText(getApplicationContext(), R.string.profileSave, Toast.LENGTH_LONG).show();
                    currentUser.setEmail(editEmail.getText().toString());
                    currentUser.setFirstname(editFirstname.getText().toString());
                    currentUser.setLastname(editLastname.getText().toString());
                    currentUser.setPhone(editPhone.getText().toString());
                    currentUser.getAddress().setStreet(editStreet.getText().toString());
                    currentUser.getAddress().setNumber(editNumberHouse.getText().toString());
                    currentUser.getAddress().setZipCode(Integer.parseInt(editZipCode.getText().toString()));
                    currentUser.getAddress().setCity(editLocality.getText().toString());

                    userRepository = new UserRepository(getApplicationContext());
                    userRepository.updateUser(currentUser);

                    Intent intent = new Intent(getBaseContext(), UserInfoActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    public void bindEditTextId(){
        editFirstname = (EditText) findViewById(R.id.editFirstname);
        editLastname = (EditText) findViewById(R.id.editLastname);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editStreet = (EditText) findViewById(R.id.editStreet);
        editNumberHouse = (EditText) findViewById(R.id.editNumberHouse);
        editZipCode = (EditText) findViewById(R.id.editZipCode);
        editLocality = (EditText) findViewById(R.id.editLocality);

        awesomeValidation.addValidation(this, R.id.editFirstname, "^[a-zA-Z\\s]+$", R.string.firstnameError); // accepte pas les accents, à améliorer si temps
        awesomeValidation.addValidation(this, R.id.editLastname, "^[a-zA-Z\\s]+$", R.string.lastnameError);

        awesomeValidation.addValidation(this, R.id.editEmail, "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", R.string.emailError);
          //General Email Regex (RFC 5322 Official Standard)

        awesomeValidation.addValidation(this, R.id.editPhone, "[0-9]{10}", R.string.phoneError);  //10

        awesomeValidation.addValidation(this, R.id.editStreet, "(.*) (.*) (.*)", R.string.streetError);

        awesomeValidation.addValidation(this, R.id.editNumberHouse, "^[a-zA-Z0-9_.-]*$", R.string.numberHouseError);

        awesomeValidation.addValidation(this, R.id.editZipCode, "[0-9]{4}", R.string.zipCodeError);

        awesomeValidation.addValidation(this, R.id.editLocality, "^^[a-zA-Z\\s]+$", R.string.localityError);

    }
}
