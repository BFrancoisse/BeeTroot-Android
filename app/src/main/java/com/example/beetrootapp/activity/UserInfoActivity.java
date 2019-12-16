package com.example.beetrootapp.activity;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beetrootapp.DAO.UserDAO;
import com.example.beetrootapp.R;
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
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        /*userDAO = new UserDAO();
        User user = userDAO.getUserById();
*/
        setButtonEditProfile();
        bindTextViewId();

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
                startActivity(intent);
            }
        });
    }
}
