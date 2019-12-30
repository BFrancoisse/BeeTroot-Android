package com.example.beetrootapp.service;

import com.example.beetrootapp.model.Picture;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PictureService {
    String BASE_URL = "https://beetrootapi.azurewebsites.net/api/Picture/";

    @GET("getPicturesByFarmId")
    Call<List<Picture>> getPicturesByFarmId(@Query("id") Integer id);
}
