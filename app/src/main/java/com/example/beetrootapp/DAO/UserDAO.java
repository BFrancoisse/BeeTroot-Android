package com.example.beetrootapp.DAO;

import android.util.Log;

import com.example.beetrootapp.model.User;
import com.example.beetrootapp.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDAO {

    private User userById;


    public User getUserById(/*int id*/){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService userService = retrofit.create(UserService.class);
        Call<User> call = userService.getUserById(1);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful())
                    return;
                userById = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            Log.i("User by Id",t.getMessage());
            }
        });
        return userById;

    }
}
