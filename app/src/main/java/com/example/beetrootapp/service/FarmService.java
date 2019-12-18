package com.example.beetrootapp.service;

import com.example.beetrootapp.model.Farm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FarmService {
    String BASE_URL = "https://beetrootapi.azurewebsites.net/api/";

    @GET("Farm/getAllFarms")
    Call<List<Farm>> getAllFarms();
}
