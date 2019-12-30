package com.example.beetrootapp.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.repository.UserRepository;
import com.example.beetrootapp.model.User;

public class UserVM extends androidx.lifecycle.ViewModel{

    private LiveData<User> userById;
    //private LiveData<Integer> userIdByEmail;
    private LiveData<User> userByEmail;

    public LiveData<User> getUserById(Context context, int id) {
        userById = new MutableLiveData<>();
        userById = loadUserById(context, id);
        return userById;
    }

   private LiveData<User> loadUserById(Context context, int id){
       UserRepository userRepository = new UserRepository(context);
       return userRepository.getUserById(id);
   }

    public LiveData<User> getUserByEmail(Context context,String email) {
        userByEmail = new MutableLiveData<>();
        UserRepository userRepository = new UserRepository(context);
        userByEmail = userRepository.getUserByEmail(email);
        return userByEmail;
    }

   /*public LiveData<Integer> getUserIdByEmail(Context context,String email){
        userIdByEmail = new MutableLiveData<>();
       UserRepository userRepository = new UserRepository(context);
       userIdByEmail = userRepository.getUserIdByEmail(email);
       return userIdByEmail;
   }*/
}
