package com.example.beetrootapp.ViewModel;

import android.content.Context;
import android.net.ConnectivityManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.model.Farm;
import com.example.beetrootapp.repository.FarmRepository;

import java.util.List;

public class FarmVM extends androidx.lifecycle.ViewModel{

    private LiveData<List<Farm>> farms;

    public LiveData<List<Farm>> getFarms(Context context) {
        farms = new MutableLiveData<>();
        farms = loadFarms(context);
        return farms;
    }

    private LiveData<List<Farm>> loadFarms(Context context){
        FarmRepository farmRepository = new FarmRepository(context);
        return farmRepository.getAllFarms();
    }
}
