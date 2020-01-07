package com.example.beetrootapp.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.R;
import com.example.beetrootapp.model.Product;
import com.example.beetrootapp.other.Constant;
import com.example.beetrootapp.other.InternetChecking;
import com.example.beetrootapp.service.ProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductRepository extends InternetChecking {
    public ProductRepository(Context context) {
        super(context);
    }

    public MutableLiveData<List<Product>> getProductsByFarmId(Integer id){
        MutableLiveData<List<Product>> mutableLiveData = new MutableLiveData<>();
        if(!super.isNetworkAvailable())
            Toast.makeText(context.getApplicationContext(), R.string.noInternet, Toast.LENGTH_LONG).show();
        else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ProductService productService = retrofit.create(ProductService.class);
            Call<List<Product>> call = productService.getProductsByFarmId(id);
            call.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    mutableLiveData.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), R.string.getProductsError, Toast.LENGTH_LONG).show();
                }
            });
        }
        return mutableLiveData;
    }
}
