package com.example.beetrootapp.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.model.Product;
import com.example.beetrootapp.repository.PictureRepository;
import com.example.beetrootapp.repository.ProductRepository;

import java.util.List;

public class ProductVM extends androidx.lifecycle.ViewModel {

    private MutableLiveData<List<Product>> productsByFarmId;
    private ProductRepository productRepository;

    public LiveData<List<Product>> getProductsByFarmId(Context context, Integer id) {
        productsByFarmId = new MutableLiveData<>();
        productRepository = new ProductRepository(context);
        productsByFarmId = productRepository.getProductsByFarmId(id);
        return productsByFarmId;
    }
}
