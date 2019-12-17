package com.example.beetrootapp.repository;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.activity.EditProfileActivity;
import com.example.beetrootapp.model.User;
import com.example.beetrootapp.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository {

    public LiveData<User> getUserById(/*int id*/){
        final MutableLiveData<User> mutableLiveData = new MutableLiveData<>();
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
            Log.i("User by Id",t.getMessage());
            }
        });
        return  mutableLiveData;
    }

    public void updateUser(User user){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService userService = retrofit.create(UserService.class);
        Call<User> call = userService.updateUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println("Ok");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }



}
