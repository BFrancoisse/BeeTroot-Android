package com.example.beetrootapp.service;

import com.example.beetrootapp.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductService {


    @GET("getProductsByFarmId")
    Call<List<Product>> getProductsByFarmId(@Query("id") Integer id);
}
