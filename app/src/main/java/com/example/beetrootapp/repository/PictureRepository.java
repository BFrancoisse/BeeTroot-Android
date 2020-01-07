package com.example.beetrootapp.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.R;
import com.example.beetrootapp.model.Picture;
import com.example.beetrootapp.other.InternetChecking;
import com.example.beetrootapp.service.PictureService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PictureRepository extends InternetChecking {
    public PictureRepository(Context context) {
        super(context);
    }

    public MutableLiveData<List<Picture>> getPicturesByFarmId(Integer id){
        final MutableLiveData<List<Picture>> mutableLiveData = new MutableLiveData<>();
        if(!super.isNetworkAvailable())
            Toast.makeText(context.getApplicationContext(), R.string.noInternet, Toast.LENGTH_LONG).show();
        else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(PictureService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            PictureService pictureService = retrofit.create(PictureService.class);
            Call<List<Picture>> call = pictureService.getPicturesByFarmId(id);
            call.enqueue(new Callback<List<Picture>>() {
                @Override
                public void onResponse(Call<List<Picture>> call, Response<List<Picture>> response) {
                    mutableLiveData.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Picture>> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), R.string.getPicturesError, Toast.LENGTH_LONG).show();
                }
            });
        }
        return mutableLiveData;
    }
}
