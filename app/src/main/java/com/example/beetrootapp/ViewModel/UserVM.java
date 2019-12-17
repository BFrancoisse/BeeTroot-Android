package com.example.beetrootapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.repository.UserRepository;
import com.example.beetrootapp.model.User;

public class UserVM extends androidx.lifecycle.ViewModel{

    private LiveData<User> userById;

    public LiveData<User> getUserById() {
        userById = new MutableLiveData<>();
        userById = loadUserById();
        return userById;
    }

   private LiveData<User> loadUserById(){
       UserRepository userRepository = new UserRepository();
       return userRepository.getUserById();
   }
}
