package com.example.beetrootapp.service;

import com.example.beetrootapp.model.Like;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LikeService {
    String BASE_URL = "https://beetrootapi.azurewebsites.net/api/";

    @GET("like/getAllLikeByIdUser")
    Call<List<Like>> getAllLikeByIdUser(@Query("id")Integer id);

    @POST("like/newLike")
    Call<Like> newLike(@Body Like like);

    @DELETE("like/deleteLike")
    Call<Like> deleteLike(@Body Like like);


}
