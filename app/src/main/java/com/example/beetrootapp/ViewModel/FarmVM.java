package com.example.beetrootapp.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.model.Farm;
import com.example.beetrootapp.repository.FarmRepository;

import java.util.List;

public class FarmVM extends androidx.lifecycle.ViewModel{

    private MutableLiveData<List<Farm>> farms;
    private LiveData<Farm> farm;
    private FarmRepository farmRepository;

    public LiveData<Farm> getFarmByUserId(int id, Context context) {
        farmRepository = new FarmRepository(context);
        farm = farmRepository.getFarmByUserId(id);
        return farm;
    }

    public LiveData<Farm> getFarmByEmail(String email, Context context) {
        farmRepository = new FarmRepository(context);
        farm = farmRepository.getFarmByEmail(email);
        return farm;
    }

    public MutableLiveData<List<Farm>> getFarms(Context context) {
        FarmRepository farmRepository = new FarmRepository(context);
        farms = new MutableLiveData<>();
        farms = farmRepository.getAllFarms();
        return farms;
    }
}
