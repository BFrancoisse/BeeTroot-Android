package com.example.beetrootapp.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.R;
import com.example.beetrootapp.model.Like;
import com.example.beetrootapp.other.Constant;
import com.example.beetrootapp.other.InternetChecking;
import com.example.beetrootapp.service.LikeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LikeRepository extends InternetChecking {
    public LikeRepository(Context context) {
        super(context);
    }

    public LiveData<List<Like>> getAllLikeByIdUser(Integer id){
        final MutableLiveData<List<Like>> mutableLiveData = new MutableLiveData<>();
        if(!super.isNetworkAvailable())
            Toast.makeText(context.getApplicationContext(), R.string.noInternet, Toast.LENGTH_LONG).show();
        else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL_API)
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
                    Toast.makeText(context.getApplicationContext(), R.string.getAllLikesError, Toast.LENGTH_LONG).show();

                }
            });
        }
        return mutableLiveData;
    }



    public void newLike(Like like){
        if(!super.isNetworkAvailable())
            Toast.makeText(context.getApplicationContext(), R.string.noInternet, Toast.LENGTH_LONG).show();
        else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            LikeService likeService = retrofit.create(LikeService.class);
            Call<Like> call = likeService.newLike(like);
            call.enqueue(new Callback<Like>() {
                @Override
                public void onResponse(Call<Like> call, Response<Like> response) {
                    Toast.makeText(context.getApplicationContext(), R.string.newLikeOk, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Like> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), R.string.newLikeError, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void deleteLike(Like like) {
        if (!super.isNetworkAvailable())
            Toast.makeText(context.getApplicationContext(), R.string.noInternet, Toast.LENGTH_LONG).show();
        else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            LikeService likeService = retrofit.create(LikeService.class);
            Call<Like> call = likeService.deleteLike(like);
            call.enqueue(new Callback<Like>() {
                @Override
                public void onResponse(Call<Like> call, Response<Like> response) {
                    Toast.makeText(context.getApplicationContext(), R.string.deleteLikeOk, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Like> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), R.string.deleteLikeError, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
