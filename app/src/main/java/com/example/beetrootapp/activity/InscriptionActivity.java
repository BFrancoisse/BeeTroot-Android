package com.example.beetrootapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.beetrootapp.R;
import com.example.beetrootapp.model.User;
import com.example.beetrootapp.repository.UserRepository;

public class InscriptionActivity extends AppCompatActivity {

    private UserRepository userRepository;
    private AwesomeValidation awesomeValidation;
    private EditText editTextFirstname;
    private EditText editTextLastname;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonIns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        bindById();
        setButtonInscription();
    }

    public void bindById(){
        editTextFirstname = (EditText)findViewById(R.id.firstname_ins);
        editTextLastname = (EditText)findViewById(R.id.lastname_ins);
        editTextEmail = (EditText)findViewById(R.id.email_ins);
        editTextPassword = (EditText)findViewById(R.id.password_ins);
        editTextConfirmPassword = (EditText)findViewById(R.id.confirmPassword_ins);

        awesomeValidation.addValidation(this, R.id.firstname_ins, "^[a-zA-Z\\s]+$", R.string.firstnameError); // accepte pas les accents, à améliorer si temps
        awesomeValidation.addValidation(this, R.id.lastname_ins, "^[a-zA-Z\\s]+$", R.string.lastnameError);

        //General Email Regex (RFC 5322 Official Standard)
        awesomeValidation.addValidation(this, R.id.email_ins, "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", R.string.emailError);

        awesomeValidation.addValidation(this,R.id.password_ins,(".{8,}"),R.string.passwordError);
        awesomeValidation.addValidation(this,R.id.confirmPassword_ins,R.id.password_ins,R.string.confirmPasswordError);

        buttonIns = (Button)findViewById(R.id.button_inscription_confirm);
    }

    public void setButtonInscription(){
        buttonIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRepository = new UserRepository(getApplicationContext());

                if (awesomeValidation.validate()) {
                    User newUser = new User(0, editTextEmail.getText().toString(), editTextFirstname.getText().toString(), editTextLastname.getText().toString(), editTextPassword.getText().toString(), null, 0, null, null, null, 0);

                    userRepository = new UserRepository(getApplicationContext());
                    userRepository.newUser(newUser);
                    Toast.makeText(getApplicationContext(), R.string.profileSave, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getBaseContext(), ConnectionActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        });
    }
}
