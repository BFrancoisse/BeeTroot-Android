package com.example.beetrootapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.model.Like;
import com.example.beetrootapp.repository.LikeRepository;

import java.util.List;

public class LikeVM {

    private LiveData<List<Like>> likesByUserId;

    public LiveData<List<Like>> getLikesByUserId(Integer id) {
        likesByUserId = new MutableLiveData<>();
        likesByUserId = loadLikesByUserId(id);
        return likesByUserId;
    }

    private LiveData<List<Like>> loadLikesByUserId(Integer id){
        LikeRepository likeRepository = new LikeRepository();
        return likeRepository.getAllLikeByIdUser(id);
    }
}
