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

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.beetrootapp.R;
import com.example.beetrootapp.ViewModel.JwtVM;
import com.example.beetrootapp.ViewModel.UserVM;
import com.example.beetrootapp.model.JwtToken;
import com.example.beetrootapp.model.Login;

public class ConnectionActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button button_connection;
    private AwesomeValidation awesomeValidation;
    private JwtVM jwtVM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        bindById();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == 16908332) {
            Intent start = new Intent(this, StartActivity.class);
            startActivity(start);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void bindById(){
        editTextEmail = (EditText)findViewById(R.id.email_connection);
        editTextPassword = (EditText)findViewById(R.id.password_connection);
        button_connection = (Button) findViewById(R.id.button_connection_to_app);

        awesomeValidation.addValidation(this, R.id.email_connection, "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", R.string.emailError);

        awesomeValidation.addValidation(this,R.id.password_connection,(".{4,}"),R.string.passwordError);

        button_connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                   getToken();
                }
            }
        });
    }
    public void getToken(){
        jwtVM = ViewModelProviders.of(this).get(JwtVM.class);
        Login currentLogin = new Login(editTextEmail.getText().toString(),editTextPassword.getText().toString());
        jwtVM.getToken(getApplicationContext(),currentLogin).observe(this,jwtToken ->{
            JwtToken jwtT =jwtToken;
            if(jwtT!= null)
            {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("userEmail",currentLogin.getEmail());
                startActivity(intent);
                finish();
            }
            else
                Toast.makeText(getApplicationContext(), R.string.connectionFailded, Toast.LENGTH_LONG).show();

        });
    }
}
