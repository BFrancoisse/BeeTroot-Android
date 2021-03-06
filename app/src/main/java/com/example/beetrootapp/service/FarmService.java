package com.example.beetrootapp.service;

import com.example.beetrootapp.model.Farm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface FarmService {
    String BASE_URL = "https://beetrootapi.azurewebsites.net/api/";

    @GET("Farm/getAllFarms")
    Call<List<Farm>> getAllFarms();

    @GET("Farm/getByUserId")
    Call<Farm> getFarmByUserId(@Query("id")Integer id);

    @GET("Farm/getFarmByEmail")
    Call<Farm> getFarmByEmail(@Query("email")String email);

    @PUT("user/updateFarm")
    Call<Farm> updateFarm(@Body Farm farm);
}
