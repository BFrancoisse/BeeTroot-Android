package com.example.beetrootapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.model.Like;
import com.example.beetrootapp.service.LikeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LikeRepository {


    public LiveData<List<Like>> getAllLikeByIdUser(Integer id){
        final MutableLiveData<List<Like>> mutableLiveData = new MutableLiveData<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LikeService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LikeService farmService = retrofit.create(LikeService.class);
        Call<List<Like>> call = farmService.getAllLikeByIdUser(id);
        call.enqueue(new Callback<List<Like>>() {
            @Override
            public void onResponse(Call<List<Like>> call, Response<List<Like>> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Like>> call, Throwable t) {
                Log.e("Get All Farms", t.getMessage());
            }
        });
        return mutableLiveData;
    }



    public void newLike(Like like){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LikeService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LikeService likeService = retrofit.create(LikeService.class);
        Call<Like> call = likeService.newLike(like);
        call.enqueue(new Callback<Like>() {
            @Override
            public void onResponse(Call<Like> call, Response<Like> response) {
                System.out.println("New Like ok");
            }

            @Override
            public void onFailure(Call<Like> call, Throwable t) {
                System.out.println("failure");
            }
        });
    }

    public void deleteLike(Like like){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LikeService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LikeService likeService = retrofit.create(LikeService.class);
        Call<Like> call = likeService.deleteLike(like);
        call.enqueue(new Callback<Like>() {
            @Override
            public void onResponse(Call<Like> call, Response<Like> response) {
                System.out.println("Delete like ok");
            }

            @Override
            public void onFailure(Call<Like> call, Throwable t) {
                System.out.println("failure");
            }
        });
    }

}
