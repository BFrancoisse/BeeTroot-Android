package com.example.beetrootapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import com.example.beetrootapp.R;
import com.example.beetrootapp.ViewModel.UserVM;
import com.example.beetrootapp.model.User;

public class UserInfoActivity extends AppCompatActivity {
    private Button buttonEditProfile;
    private TextView txtFirstname;
    private TextView txtLastname;
    private TextView txtEmail;
    private TextView txtPhone;
    private TextView txtStreet;
    private TextView txtNumberHouse;
    private TextView txtZipCode;
    private TextView txtLocality;
    private TextView txtFarmName;
    private TextView txtFarmPhone;

    private UserVM userVM;
    private User uUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.myProfile);
        setContentView(R.layout.activity_user_info);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bindTextViewId();
        setTextViewValues();
        setButtonEditProfile();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(item.getItemId() == 16908332)
            this.finish();

        return super.onOptionsItemSelected(item);
    }

    public void setTextViewValues(){
        userVM = ViewModelProviders.of(this).get(UserVM.class);
        userVM.getUserById(getApplicationContext()).observe(this, user -> {
            txtFirstname.setText(user.getFirstname());
            txtLastname.setText(user.getLastname());
            txtEmail.setText(user.getEmail());
            txtPhone.setText(user.getPhone());
            txtStreet.setText(user.getAddress().getStreet());
            txtNumberHouse.setText(user.getAddress().getNumber());
            txtZipCode.setText(String.valueOf(user.getAddress().getZipCode()));
            txtLocality.setText(user.getAddress().getCity());

            uUser = user;
        });
    }

    public void bindTextViewId(){
        txtFirstname = (TextView) findViewById(R.id.txtFirstname);
        txtLastname = (TextView) findViewById(R.id.txtLastname);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtPhone = (TextView) findViewById(R.id.txtPhone);
        txtStreet = (TextView) findViewById(R.id.txtStreet);
        txtNumberHouse = (TextView) findViewById(R.id.txtNumberHouse);
        txtZipCode = (TextView) findViewById(R.id.txtZipCode);
        txtLocality = (TextView) findViewById(R.id.txtLocality);
        txtFarmName = (TextView) findViewById(R.id.txtFarmName);
        txtFarmPhone = (TextView) findViewById(R.id.txtFarmPhone);

    }
    public void setButtonEditProfile(){
        buttonEditProfile = (Button) findViewById(R.id.editProfile);

        buttonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditProfileActivity.class);
                intent.putExtra("currentUser",uUser);
                startActivity(intent);
                finish();
            }
        });
    }
}
