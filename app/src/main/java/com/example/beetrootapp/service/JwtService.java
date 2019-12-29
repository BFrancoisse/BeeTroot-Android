package com.example.beetrootapp.service;

import com.example.beetrootapp.model.JwtToken;
import com.example.beetrootapp.model.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JwtService {

    String BASE_URL = "https://beetrootapi.azurewebsites.net/api/";

    @POST("Jwt")
    Call<JwtToken> getToken(@Body Login login);
}
