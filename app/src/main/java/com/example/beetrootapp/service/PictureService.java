package com.example.beetrootapp.service;

import com.example.beetrootapp.model.Picture;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PictureService {


    @GET("getPicturesByFarmId")
    Call<List<Picture>> getPicturesByFarmId(@Query("id")Integer id);
}
