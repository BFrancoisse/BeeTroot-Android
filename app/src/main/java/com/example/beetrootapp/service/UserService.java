package com.example.beetrootapp.service;

import com.example.beetrootapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserService {
    String BASE_URL = "https://beetrootapi.azurewebsites.net/api/";

    @GET("user/getUserById")
    Call<User> getUserById(@Query("id")Integer id);

    @GET("user/getUserByEmail")
    Call<User> getUserByEmail(@Query("email")String email);

    @PUT("user/updateUser")
    Call<User> updateUser(@Body User user);

    @POST("user/newUser")
    Call<User> newUser(@Body User user);



}
