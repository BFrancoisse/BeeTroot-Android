package com.example.beetrootapp.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.R;
import com.example.beetrootapp.model.Farm;
import com.example.beetrootapp.other.InternetChecking;
import com.example.beetrootapp.service.FarmService;

import com.example.beetrootapp.other.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FarmRepository extends InternetChecking {

    public FarmRepository(Context context) {
        super(context);
    }

    public MutableLiveData<List<Farm>> getAllFarms(){
        final MutableLiveData<List<Farm>> mutableLiveData = new MutableLiveData<>();
        if(!super.isNetworkAvailable())
            Toast.makeText(context.getApplicationContext(), R.string.noInternet, Toast.LENGTH_LONG).show();
        else {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL_API)
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
                    Toast.makeText(context, R.string.getAllFarmsError, Toast.LENGTH_LONG).show();
                }
            });
        }
            return mutableLiveData;

    }

    public LiveData<Farm> getFarmByUserId(int id) {
        final MutableLiveData<Farm> mutableLiveData = new MutableLiveData<>();
        if(!super.isNetworkAvailable())
            Toast.makeText(context.getApplicationContext(), R.string.noInternet, Toast.LENGTH_LONG).show();
        else {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            FarmService farmService = retrofit.create(FarmService.class);
            Call<Farm> call = farmService.getFarmByUserId(id);
            call.enqueue(new Callback<Farm>() {
                @Override
                public void onResponse(Call<Farm> call, Response<Farm> response) {
                    mutableLiveData.setValue(response.body());
                }

                @Override
                public void onFailure(Call<Farm> call, Throwable t) {
                    Toast.makeText(context, R.string.farmByIdError, Toast.LENGTH_LONG).show();
                }
            });
        }
        return mutableLiveData;
    }

    public LiveData<Farm> getFarmByEmail(String email) {
        final MutableLiveData<Farm> mutableLiveData = new MutableLiveData<>();
        if(!super.isNetworkAvailable())
            Toast.makeText(context.getApplicationContext(), R.string.noInternet, Toast.LENGTH_LONG).show();
        else {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            FarmService farmService = retrofit.create(FarmService.class);
            Call<Farm> call = farmService.getFarmByEmail(email);
            call.enqueue(new Callback<Farm>() {
                @Override
                public void onResponse(Call<Farm> call, Response<Farm> response) {
                    mutableLiveData.setValue(response.body());
                }

                @Override
                public void onFailure(Call<Farm> call, Throwable t) {
                    Toast.makeText(context, R.string.farmByEmailError, Toast.LENGTH_LONG).show();
                }
            });
        }
        return mutableLiveData;
    }

    public void updateFarm(Farm farm){
        if(!super.isNetworkAvailable())
            Toast.makeText(context.getApplicationContext(), R.string.noInternet, Toast.LENGTH_LONG).show();
        else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            FarmService userService = retrofit.create(FarmService.class);
            Call<Farm> call = userService.updateFarm(farm);
            call.enqueue(new Callback<Farm>() {
                @Override
                public void onResponse(Call<Farm> call, Response<Farm> response) {
                    Toast.makeText(context, R.string.updateFarmOk, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Farm> call, Throwable t) {
                    Toast.makeText(context, R.string.updateFarmError, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
