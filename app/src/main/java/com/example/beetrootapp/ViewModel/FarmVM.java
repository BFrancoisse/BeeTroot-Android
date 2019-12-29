package com.example.beetrootapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.model.Farm;
import com.example.beetrootapp.repository.FarmRepository;

import java.util.List;

public class FarmVM extends androidx.lifecycle.ViewModel{

    private LiveData<List<Farm>> farms;
    private LiveData<Farm> farm;

    public LiveData<List<Farm>> getFarms() { //tt
        farms = new MutableLiveData<>();
        farms = loadFarms();
        return farms;
    }

    public LiveData<Farm> getFarmByUserId(int id) {
        FarmRepository farmRepository = new FarmRepository();
        farm = new MutableLiveData<>();
        farm = farmRepository.getFarmByUserId(id);
        return farm;
    }

    private LiveData<List<Farm>> loadFarms(){
        FarmRepository farmRepository = new FarmRepository();
        return farmRepository.getAllFarms();
    }
}
