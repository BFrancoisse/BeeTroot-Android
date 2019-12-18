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
import androidx.lifecycle.ViewModelProviders;

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

    private UserVM userVM;
    private User currentUser;
    private UserRepository userRepository;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         currentUser = (User) getIntent().getSerializableExtra("currentUser");

        bindEditTextId();
        setEditTextValues();

        setButtonSaveProfile();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(item.getItemId() == 16908332) {
            Intent intent = new Intent(this, UserInfoActivity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

   public void setEditTextValues(){
        userVM = ViewModelProviders.of(this).get(UserVM.class);
        userVM.getUserById().observe(this, user -> {
            editFirstname.setText(user.getFirstname());
            editLastname.setText(user.getLastname());
            editEmail.setText(user.getEmail());
            editPhone.setText(user.getPhone());
            editStreet.setText(user.getAddress().getStreet());
            editNumberHouse.setText(user.getAddress().getNumber());
            editZipCode.setText("" + user.getAddress().getZipCode());
            editLocality.setText(user.getAddress().getCity());
            /*txtFirstname.setText(user.getFirstname());
            txtFirstname.setText(user.getFirstname());
            */
        });
    }

    public void setButtonSaveProfile(){
        saveProfile = (Button) findViewById(R.id.saveProfile);
        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Profil sauvegardé",Toast.LENGTH_LONG).show();
                currentUser.setEmail(editEmail.getText().toString());
                currentUser.setFirstname(editFirstname.getText().toString());
                currentUser.setLastname(editLastname.getText().toString());
                currentUser.setPhone(editPhone.getText().toString());
                currentUser.getAddress().setStreet(editStreet.getText().toString());
                currentUser.getAddress().setNumber(editNumberHouse.getText().toString());
                currentUser.getAddress().setZipCode(Integer.parseInt(editZipCode.getText().toString()));
                currentUser.getAddress().setCity(editLocality.getText().toString());


                userRepository.updateUser(currentUser);

                //User user = new User()


                Intent intent = new Intent(getBaseContext(), UserInfoActivity.class);
                startActivity(intent);
                finish();
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
    }
}
