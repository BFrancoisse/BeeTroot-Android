package com.example.beetrootapp.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.model.Picture;
import com.example.beetrootapp.repository.PictureRepository;

import java.util.List;

public class PictureVM  extends androidx.lifecycle.ViewModel {

    private MutableLiveData<List<Picture>> picturesByFarmId;
    private PictureRepository pictureRepository;

    public LiveData<List<Picture>> getPicturesByFarmId(Context context, Integer id) {
        picturesByFarmId = new MutableLiveData<>();
        pictureRepository = new PictureRepository(context);
        picturesByFarmId = pictureRepository.getPicturesByFarmId(id);
        return picturesByFarmId;
    }
}
