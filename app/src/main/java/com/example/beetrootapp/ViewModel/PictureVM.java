package com.example.beetrootapp.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.beetrootapp.model.Picture;
import com.example.beetrootapp.repository.PictureRepository;

import java.util.List;

public class PictureVM  extends ViewModel {

    private MutableLiveData<List<Picture>> picturesByFarmId;
    private PictureRepository pictureRepository;

    public MutableLiveData<List<Picture>> getPicturesByFarmId(Context context, Integer id) {
        pictureRepository = new PictureRepository(context);
        picturesByFarmId = new MutableLiveData<>();
        picturesByFarmId = pictureRepository.getPicturesByFarmId(id);
        return picturesByFarmId;
    }
}
