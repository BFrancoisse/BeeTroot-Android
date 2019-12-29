package com.example.beetrootapp.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.R;
import com.example.beetrootapp.model.Farm;
import com.example.beetrootapp.model.User;
import com.example.beetrootapp.other.InternetChecking;
import com.example.beetrootapp.service.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository extends InternetChecking{


    public UserRepository(Context context) {
        super(context);
    }

    public LiveData<User> getUserById(/*int id*/){
        final MutableLiveData<User> mutableLiveData = new MutableLiveData<>();
        if(!super.isNetworkAvailable())
            Toast.makeText(context.getApplicationContext(), R.string.noInternet, Toast.LENGTH_LONG).show();
        else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(UserService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserService userService = retrofit.create(UserService.class);
            Call<User> call = userService.getUserById(4);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    mutableLiveData.setValue(response.body());
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), R.string.getUserByIdError, Toast.LENGTH_LONG).show();
                }
            });
        }
        return  mutableLiveData;
    }

    public void updateUser(User user){
        if(!super.isNetworkAvailable())
            Toast.makeText(context.getApplicationContext(), R.string.noInternet, Toast.LENGTH_LONG).show();
        else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(UserService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserService userService = retrofit.create(UserService.class);
            Call<User> call = userService.updateUser(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Toast.makeText(context.getApplicationContext(), R.string.updateUserOk, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), R.string.updateUserError, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    public void newUser(User user){
        if(!super.isNetworkAvailable())
        Toast.makeText(context.getApplicationContext(), R.string.noInternet, Toast.LENGTH_LONG).show();
        else{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(UserService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserService userService = retrofit.create(UserService.class);
            Call<User> call = userService.newUser(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Toast.makeText(context.getApplicationContext(), R.string.newUserOk, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), R.string.newUserError, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public LiveData<Integer> getUserIdByEmail(String email){
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
        if(!super.isNetworkAvailable())
            Toast.makeText(context.getApplicationContext(), R.string.noInternet, Toast.LENGTH_LONG).show();
        else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(UserService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserService userService = retrofit.create(UserService.class);
            Call<Integer> call = userService.getUserIdByEmail(email);
            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    mutableLiveData.setValue(response.body());
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), R.string.getUserIdByEmailError, Toast.LENGTH_LONG).show();
                }
            });
        }
        return mutableLiveData;

    }



}
