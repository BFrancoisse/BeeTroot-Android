package com.example.beetrootapp.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.repository.UserRepository;
import com.example.beetrootapp.model.User;

public class UserVM extends androidx.lifecycle.ViewModel{

    private LiveData<User> userById;

    public LiveData<User> getUserById(Context context) {
        userById = new MutableLiveData<>();
        userById = loadUserById(context);
        return userById;
    }

   private LiveData<User> loadUserById(Context context){
       UserRepository userRepository = new UserRepository(context);
       return userRepository.getUserById();
   }
}
