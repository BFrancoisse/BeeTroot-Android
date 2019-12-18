package com.example.beetrootapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.model.Farm;
import com.example.beetrootapp.service.FarmService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FarmRepository {

    public LiveData<List<Farm>> getAllFarms(){
        final MutableLiveData<List<Farm>> mutableLiveData = new MutableLiveData<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FarmService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FarmService farmService = retrofit.create(FarmService.class);
        Call<List<Farm>> call = farmService.getAllFarms();
        call.enqueue(new Callback<List<Farm>>() {
            @Override
            public void onResponse(Call<List<Farm>> call, Response<List<Farm>> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Farm>> call, Throwable t) {
                Log.e("Get All Farms", t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
