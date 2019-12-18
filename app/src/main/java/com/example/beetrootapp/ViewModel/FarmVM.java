package com.example.beetrootapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.model.Farm;
import com.example.beetrootapp.repository.FarmRepository;

import java.util.List;

public class FarmVM extends androidx.lifecycle.ViewModel{

    private LiveData<List<Farm>> farms;

    public LiveData<List<Farm>> getFarms() { //tt
        farms = new MutableLiveData<>();
        farms = loadFarms();
        return farms;
    }

    private LiveData<List<Farm>> loadFarms(){
        FarmRepository farmRepository = new FarmRepository();
        return farmRepository.getAllFarms();
    }
}
