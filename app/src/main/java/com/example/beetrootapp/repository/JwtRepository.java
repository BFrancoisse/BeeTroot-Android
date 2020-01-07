package com.example.beetrootapp.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.R;
import com.example.beetrootapp.model.JwtToken;
import com.example.beetrootapp.model.Login;
import com.example.beetrootapp.other.Constant;
import com.example.beetrootapp.other.InternetChecking;
import com.example.beetrootapp.service.JwtService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JwtRepository extends InternetChecking {

    public JwtRepository(Context context) {
        super(context);
    }

    public LiveData<JwtToken> getToken(Login login){
        final MutableLiveData<JwtToken> mutableLiveData = new MutableLiveData<>();
        if(!super.isNetworkAvailable())
            Toast.makeText(context.getApplicationContext(), R.string.noInternet, Toast.LENGTH_LONG).show();
        else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            JwtService jwtService = retrofit.create(JwtService.class);
            Call<JwtToken> call = jwtService.getToken(login);
            call.enqueue(new Callback<JwtToken>() {
                @Override
                public void onResponse(Call<JwtToken> call, Response<JwtToken> response) {
                    mutableLiveData.setValue(response.body());
                    Toast.makeText(context, R.string.connectionValidate, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<JwtToken> call, Throwable t) {
                    Toast.makeText(context, R.string.connectionFailded, Toast.LENGTH_LONG).show();
                }
            });
        }
        return mutableLiveData;
    }
}
